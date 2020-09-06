package com.jinxi.star.jxserver.config.aspect;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 统一请求日志记录切面
 *
 * @author Shiz
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(WebLogAspect.class);
    private static final String TRACE_ID = "traceId";

    /**
     * 两个..代表所有子目录，最后括号里的两个..代表所有参数
     *
     * @param
     * @return void
     */
    @Pointcut("execution( * com.jinxi.star.jxserver.controller.*.*(..))")
    public void logPointCut() {
    }


    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String arg = "";

        for (Object o : joinPoint.getArgs()) {
            if (!(o instanceof HttpServletRequest)) {
                arg = JSON.toJSONString(o);
            }
        }

        // 记录下请求内容
        logger.info("请求地址 : " + request.getRequestURL().toString()
                + " HTTP METHOD : " + request.getMethod()
                + " CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName()
                + " 参数 : " + arg);


    }

    /**
     * returning的值和doAfterReturning的参数名一致
     *
     * @param ret
     * @return void
     */
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) {
        logger.info("返回值 : " + JSON.toJSONString(ret));
        MDC.remove(TRACE_ID);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        String traceId = UUID.randomUUID().toString().replaceAll("-", "");
        MDC.clear();
        MDC.put(TRACE_ID, traceId);
        // ob 为方法的返回值
        Object ob = pjp.proceed();
        logger.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }

}
