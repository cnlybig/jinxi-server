package com.jinxi.star.jxserver.config.http;

import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConditionalOnClass(HttpClient.class)
@ConfigurationProperties(prefix = "spring.httpclient")
public class HttpClientProperties {
    private static final Logger logger = LoggerFactory.getLogger(HttpClientProperties.class);
    /**
     * 连接池总的最大连接数，包含所有网站
     */
    private int maxTotal=100;
    /**
     * 每个外部网站tcp最大连接数
     */
    private int maxPerRoute=10;
    /**
     * 从连接池获取连接时间，毫秒,0一直等待，-1使用系统默认。
     */
    private int connectionRequestTimeout=-1;
    /**
     * 建立TCP连接时间，毫秒，0一直等待，-1使用系统默认。
     */
    private int connectTimeout=-1;
    /**
     * 数据传输超时时间，毫秒，0一直等待，-1使用系统默认。
     */
    private int socketTimeout=-1;
    /**
     * http连接的保持时间，单位分，0为该参数不启用
     */
    private int idleTime=0;
    /**
     * 是否启用tls加密
     */
    private boolean sslEnabled=false;
    /**
     * 是否对证书名是否是主机名校验
     */
    private boolean hostnameVerify=false;
    /**
     * 是否启用http代理
     */
    private boolean proxyEnabled=false;
    /**
     * 代理主机名
     */
    private String proxyHost;
    /**
     * 代理主机端口
     */
    private int proxyPort=0;
    /**
     * 代理主机协议:http,https,默认http。
     */
    private String proxyScheme="http";
    /**
     * jks配置
     */
    private Keystore keystore=new Keystore();
    /**
     * jks配置
     */
    private Truststore truststore=new Truststore();
    /**
     * 远程服务器名单
     */
    private List<RemoteServer> remotes = new ArrayList<>();

    /**
     * 私钥库配置
     */
    public static class Keystore{
        /**
         * jks文件路径
         */
        private String filename;
        /**
         * jks库密码
         */
        private String storePassword;
        /**
         * jks里面私钥密码
         */
        private String privatePassword;

        @Override
        public String toString() {
            return "Keystore{" +
                    "filename='" + filename + '\'' +
                    ", storePassword='" + storePassword + '\'' +
                    ", privatePassword='" + privatePassword + '\'' +
                    '}';
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getStorePassword() {
            return storePassword;
        }

        public void setStorePassword(String storePassword) {
            this.storePassword = storePassword;
        }

        public String getPrivatePassword() {
            return privatePassword;
        }

        public void setPrivatePassword(String privatePassword) {
            this.privatePassword = privatePassword;
        }
    }

    /**
     * 信任库配置
     */
    public static class Truststore{
        /**
         * jks文件路径
         */
        private String filename;
        /**
         * jks库密码
         */
        private String storePassword;

        @Override
        public String toString() {
            return "Truststore{" +
                    "filename='" + filename + '\'' +
                    ", storePassword='" + storePassword + '\'' +
                    '}';
        }

        public String getFilename() {
            return filename;
        }

        public void setFilename(String filename) {
            this.filename = filename;
        }

        public String getStorePassword() {
            return storePassword;
        }

        public void setStorePassword(String storePassword) {
            this.storePassword = storePassword;
        }
    }
    /**
     * 远程连接服务器的配置
     */
    public static class RemoteServer{
        /**
         * 外部主机名或者ip地址
         */
        private String host;
        /**
         * 外部主机的端口
         */
        private int port;
        /**
         * 认证方式，none表示无，basic表示http basic认证
         */
        private String authSchema;
        /**
         * 对应外部主机，basic认证的用户名
         */
        private String username;
        /**
         * 对应外部主机，basic认证的用户密码
         */
        private String password;
        /**
         * 对应外部主机，在jks文件里面对应的私钥的库entry名字
         */
        private String keyAlias;

        @Override
        public String toString() {
            return "RemoteServer{" +
                    "host='" + host + '\'' +
                    ", port=" + port +
                    ", authSchema='" + authSchema + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", keyAlias='" + keyAlias + '\'' +
                    '}';
        }

        public String getHost() {
            return host;
        }

        public void setHost(String host) {
            this.host = host;
        }

        public int getPort() {
            return port;
        }

        public void setPort(int port) {
            this.port = port;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getKeyAlias() {
            return keyAlias;
        }

        public void setKeyAlias(String keyAlias) {
            this.keyAlias = keyAlias;
        }

        public String getAuthSchema() {
            return authSchema;
        }

        public void setAuthSchema(String authSchema) {
            this.authSchema = authSchema;
        }
    }

    @Override
    public String toString() {
        return "HttpClientProperties{" +
                "maxTotal=" + maxTotal +
                ", maxPerRoute=" + maxPerRoute +
                ", connectionRequestTimeout=" + connectionRequestTimeout +
                ", connectTimeout=" + connectTimeout +
                ", socketTimeout=" + socketTimeout +
                ", keepAliveTime=" + idleTime +
                ", sslEnabled=" + sslEnabled +
                ", hostnameVerify=" + hostnameVerify +
                ", keystore=" + keystore +
                ", remotes=" + remotes +
                '}';
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public int getConnectionRequestTimeout() {
        return connectionRequestTimeout;
    }

    public void setConnectionRequestTimeout(int connectionRequestTimeout) {
        this.connectionRequestTimeout = connectionRequestTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSocketTimeout() {
        return socketTimeout;
    }

    public void setSocketTimeout(int socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

    public boolean isSslEnabled() {
        return sslEnabled;
    }

    public void setSslEnabled(boolean sslEnabled) {
        this.sslEnabled = sslEnabled;
    }

    public List<RemoteServer> getRemotes() {
        return remotes;
    }

    public void setRemotes(List<RemoteServer> remotes) {
        this.remotes = remotes;
    }

    public Keystore getKeystore() {
        return keystore;
    }

    public void setKeystore(Keystore keystore) {
        this.keystore = keystore;
    }

    public Truststore getTruststore() {
        return truststore;
    }

    public void setTruststore(Truststore truststore) {
        this.truststore = truststore;
    }

    public boolean isHostnameVerify() {
        return hostnameVerify;
    }

    public void setHostnameVerify(boolean hostnameVerify) {
        this.hostnameVerify = hostnameVerify;
    }

    public int getIdleTime() {
        return idleTime;
    }

    public void setIdleTime(int idleTime) {
        this.idleTime = idleTime;
    }

    public boolean isProxyEnabled() {
        return proxyEnabled;
    }

    public void setProxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled = proxyEnabled;
    }

    public String getProxyHost() {
        return proxyHost;
    }

    public void setProxyHost(String proxyHost) {
        this.proxyHost = proxyHost;
    }

    public int getProxyPort() {
        return proxyPort;
    }

    public void setProxyPort(int proxyPort) {
        this.proxyPort = proxyPort;
    }

    public String getProxyScheme() {
        return proxyScheme;
    }

    public void setProxyScheme(String proxyScheme) {
        this.proxyScheme = proxyScheme;
    }
}
