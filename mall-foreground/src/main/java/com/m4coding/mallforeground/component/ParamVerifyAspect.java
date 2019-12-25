package com.m4coding.mallforeground.component;

import com.m4coding.mallbase.api.CommonResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * 接口入参检验切面
 * 针对参数中添加@Vaild注解与BindingResult
 */
@Aspect
@Component
@Order(2)
public class ParamVerifyAspect {

    //统一定义切点
    @Pointcut("execution(public * com.m4coding.mallforeground.controller.*.*(..))")
    public void paramVerify() {

    }

    @Around("paramVerify()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if (fieldError != null) {
                        return CommonResult.validateFailed(fieldError.getDefaultMessage());
                    } else {
                        return CommonResult.validateFailed();
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

}
