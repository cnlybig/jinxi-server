package com.jinxi.star.jxserver.config.http;

import com.google.common.base.Strings;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.client.AuthCache;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableConfigurationProperties(HttpClientProperties.class)
@ConditionalOnClass(HttpClient.class)
public class HttpClientConfig implements DisposableBean {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientConfig.class);

    @Autowired
    private HttpClientProperties props;
    private CloseableHttpClient httpClient;
    private IdleConnectionMonitorThread idleConnectionMonitorThread;
    private final CountDownLatch countDownLatch = new CountDownLatch(1);
    private static final String BASIC_NAME = "basic";

    /**
     * 定制请求参数，包括proxy设置
     *
     * @return
     */
    @Bean
    public RequestConfig requestConfig() {
        HttpHost proxy = null;
        if (props.isProxyEnabled()) {
            proxy = new HttpHost(props.getProxyHost(), props.getProxyPort(), props.getProxyScheme());
        }
        RequestConfig config = RequestConfig.custom()
                .setConnectionRequestTimeout(props.getConnectionRequestTimeout())
                .setConnectTimeout(props.getConnectTimeout())
                .setSocketTimeout(props.getSocketTimeout())
                .setProxy(proxy)
                .build();
        return config;
    }


    /**
     * 定制连接池
     *
     * @return
     * @throws Exception
     */
    @Bean
    public HttpClientConnectionManager connectionManager() throws Exception {
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial((chain, authType) -> true).build();
        RegistryBuilder<ConnectionSocketFactory> socketFactoryRegistryBuilder = RegistryBuilder.create();
        socketFactoryRegistryBuilder.register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslContext));

        Registry<ConnectionSocketFactory> socketFactoryRegistry = socketFactoryRegistryBuilder.build();

        //socket 设置
        SocketConfig socketConfig = SocketConfig.custom()
                .setSoKeepAlive(false)
                .setTcpNoDelay(true)
                .build();

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
        cm.setMaxTotal(props.getMaxTotal());
        cm.setDefaultMaxPerRoute(props.getMaxPerRoute());
        cm.setDefaultSocketConfig(socketConfig);
        //启动连接池监听线程
        idleConnectionMonitorThread = new IdleConnectionMonitorThread(cm, props.getIdleTime(), countDownLatch);
        idleConnectionMonitorThread.start();
        return cm;
    }

    @Bean
    public HttpRequestRetryHandler httpRequestRetryHandler() {
        HttpRequestRetryHandler retryHandler = new CustomizedHttpRequestRetryHandler();
        return retryHandler;
    }


    @Bean
    public CloseableHttpClient httpClient(RequestConfig requestConfig,
                                          HttpClientConnectionManager connectionManager,
                                          HttpRequestRetryHandler retryHandler) {

        httpClient = HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(requestConfig)
                .setRetryHandler(retryHandler)
                .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                .build();

        logger.info("Httpclient 设置成功！");
        return httpClient;
    }

    @Bean
    public ClientHttpRequestFactory httpComponentsClientHttpRequestFactory(HttpClient httpClient) {
        HttpComponentsClientHttpRequestFactory hcrf = new HttpComponentsClientHttpRequestFactory(httpClient) {
            @Override
            protected HttpContext createHttpContext(HttpMethod httpMethod, URI uri) {
                AuthCache authCache = new BasicAuthCache();
                BasicScheme basicScheme = new BasicScheme();
                props.getRemotes().stream().forEach(remote -> {
                    if (BASIC_NAME.equalsIgnoreCase(Strings.nullToEmpty(remote.getAuthSchema()).trim())) {
                        authCache.put(new HttpHost(remote.getHost(), remote.getPort()), basicScheme);
                    }
                });
                HttpClientContext localContext = HttpClientContext.create();
                localContext.setAuthCache(authCache);
                return localContext;
            }
        };
        return hcrf;
    }

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        for (HttpMessageConverter converter : restTemplate.getMessageConverters()) {
            if (converter instanceof StringHttpMessageConverter) {
                ((StringHttpMessageConverter) converter).setWriteAcceptCharset(false);
            }
        }
        return restTemplate;
    }

    @Bean
    public Executor httpClientExecutor() {
        return Executor.newInstance(httpClient);
    }

    @Override
    public void destroy() throws Exception {
        if (Objects.nonNull(idleConnectionMonitorThread)) {
            idleConnectionMonitorThread.shutdown();
        }
        //等待连接池任务完成
        countDownLatch.await(10, TimeUnit.SECONDS);
        if (Objects.nonNull(httpClient)) {
            httpClient.close();
        }
    }

    /**
     * 独立线程来清理长期空闲的连接和http keepalive timeout的连接
     */
    public static class IdleConnectionMonitorThread extends Thread {
        private static final Logger logger = LoggerFactory.getLogger(IdleConnectionMonitorThread.class);
        private final HttpClientConnectionManager connMgr;
        private volatile boolean shutdown = false;
        private CountDownLatch countDownLatch;
        private int idleTime;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr, int idleTime, CountDownLatch countDownLatch) {
            super("HttpClientIdleMonitorThread");
            this.connMgr = connMgr;
            this.countDownLatch = countDownLatch;
            this.idleTime = idleTime;
        }

        @Override
        public void run() {
            try {
                while (!shutdown) {
                    synchronized (this) {
                        wait(5000);
                        // Close expired connections
                        connMgr.closeExpiredConnections();
                        // Optionally, close connections
                        if (idleTime > 0) {
                            connMgr.closeIdleConnections(idleTime, TimeUnit.MINUTES);
                        }
                    }
                }
            } catch (InterruptedException e) {
                logger.error("连接池监听程序退出", e);
            } finally {
                countDownLatch.countDown();
            }
        }

        public void shutdown() {
            shutdown = true;
            synchronized (this) {
                notifyAll();
            }
        }
    }

    /**
     * Request请求失败后再次重试的实现
     */
    private static class CustomizedHttpRequestRetryHandler implements HttpRequestRetryHandler {
        private static final Logger logger = LoggerFactory.getLogger(CustomizedHttpRequestRetryHandler.class);

        @Override
        public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
            logger.info("retry 重试！！！");
            if (executionCount >= 1) {
                // Do not retry if over max retry count
                return false;
            }
            if (exception instanceof ConnectTimeoutException) {
                // Connection refused
                return true;
            } else if (exception instanceof InterruptedIOException) {
                // Timeout
                return false;
            } else if (exception instanceof UnknownHostException) {
                // Unknown host
                return true;
            } else if (exception instanceof SSLException) {
                // SSL handshake exception
                return false;
            } else if (exception instanceof IOException) {
                // Other socket exception
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }
            return false;
        }
    }

    public HttpClientProperties getProps() {
        return props;
    }

    public void setProps(HttpClientProperties props) {
        this.props = props;
    }

    public CloseableHttpClient getHttpClient() {
        return httpClient;
    }

    public void setHttpClient(CloseableHttpClient httpClient) {
        this.httpClient = httpClient;
    }
}
