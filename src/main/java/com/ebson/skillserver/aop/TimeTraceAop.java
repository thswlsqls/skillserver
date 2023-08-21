package com.ebson.skillserver.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@Aspect
@Component
public class TimeTraceAop {

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
