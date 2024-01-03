package com.project.ychengspmall.common.log.aspect;

import com.project.ychengspmall.common.log.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {            // 环绕通知切面类定义

    @Around(value = "@annotation(sysLog)")
    public Object doAroundAdvice(ProceedingJoinPoint joinPoint , Log sysLog) {
        String title = sysLog.title();
        log.info("LogAspect...doAroundAdvice has been executed...."+title);
        System.out.println("LogAspect...doAroundAdvice has been executed...."+title);
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();              // 执行业务方法
        } catch (Throwable e) {                         // 代码执行进入到catch中，业务方法执行产生异常
            throw new RuntimeException(e);
        }
        return proceed ;                                // 返回执行结果
    }
}
