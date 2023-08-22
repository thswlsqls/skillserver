package com.ebson.skillserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

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

//    @AfterReturning(pointcut = "getMappingPointcut()", returning = "result")
//    public void afterReturningGetMapping(Joinpoint joinpoint, Object result) throws Throwable {
//        log.info("getMapping method returning - " + result);
//    }
//
//    @AfterReturning(pointcut = "postMappingPointcut()", returning = "result")
//    public void afterReturningPostMapping(Joinpoint joinpoint, Object result) throws Throwable {
//        log.info("postMapping method returning - " + result);
//    }

}
