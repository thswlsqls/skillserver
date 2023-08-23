package com.ebson.skillserver.config.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class TestAopConfig {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void getMappingPointcut(){}

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void postMappingPointcut(){}

    @Before(value = "getMappingPointcut()")
    public void beforeGetMapping() throws Throwable {
        log.info("getMapping started ... ");
    }

    @After(value = "getMappingPointcut()")
    public void afterGetMapping() throws Throwable {
        log.info("getMapping finished ... ");
    }

    @Before(value = "postMappingPointcut()")
    public void beforePostMapping() throws Throwable {
        log.info("postMapping started ... ");
    }

    @After(value = "postMappingPointcut()")
    public void afterPostMapping() throws Throwable {
        log.info("postMapping finished ... ");
    }

    @Around("execution(* com.ebson.skillserver..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
        long start = System.currentTimeMillis();
        Date startDate = new Date();
        startDate.setTime(start);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss.SSS");

        log.info(joinPoint.toString() + " - Start : " + simpleDateFormat.format(startDate));
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            Date finishDate = new Date();
            finishDate.setTime(finish);
            long timeMs = finish - start;
            log.info(joinPoint.toString() + " - finish : " + simpleDateFormat.format(finishDate) + ", Elapsed time: "+ timeMs + "ms");
        }
    }

}
