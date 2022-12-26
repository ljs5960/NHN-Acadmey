package com.nhnacademy.edu.springframework.messagesender;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@EnableAspectJAutoProxy
public class StopwatchAround {
    @Around("execution(* com.nhnacademy.edu.springframework.messagesender.service.MessageSender.sendMessage(..))")
    public Object stopwatch(ProceedingJoinPoint pjp) {
        StopWatch stopWatch = new StopWatch();

        try {
            stopWatch.start();
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            stopWatch.stop();
            System.out.println(stopWatch.prettyPrint());
        }
    }
}
