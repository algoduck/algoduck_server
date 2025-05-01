package com.koreii.algoduck.base.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class ExceptionLoggingAspect {

  @Pointcut("@annotation(org.springframework.web.bind.annotation.ExceptionHandler)")
  public void exceptionHandlerMethods() {}

  @Around("exceptionHandlerMethods()")
  public Object logException(ProceedingJoinPoint joinPoint) throws Throwable {
    for (Object arg : joinPoint.getArgs()) {
      if (arg instanceof Exception exception) {
        log.error("예외 발생: [{}] - {}", exception.getClass().getSimpleName(), exception.getMessage(), exception);
      }
    }

    return joinPoint.proceed();
  }
}