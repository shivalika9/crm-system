package com.example.crm.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Log all methods in your web + service packages
    @Around("within(com.example.crm.web..*) || within(com.example.crm.service..*)")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        String clazz = pjp.getSignature().getDeclaringTypeName();
        String method = pjp.getSignature().getName();
        Object[] args = pjp.getArgs();

        long start = System.currentTimeMillis();
        log.info("→ {}.{}({})", clazz, method, safeArgs(args));

        try {
            Object result = pjp.proceed();
            long took = System.currentTimeMillis() - start;
            log.info("← {}.{}(..) [{} ms]", clazz, method, took);
            return result;
        } catch (Exception ex) {
            long took = System.currentTimeMillis() - start;
            log.error("✖ {}.{}(..) failed after {} ms: {}", clazz, method, took, ex.toString(), ex);
            throw ex;
        }
    }

    private String safeArgs(Object[] args) {
        try {
            return Arrays.toString(args);
        } catch (Exception e) {
            return "[unprintable-args]";
        }
    }
}
