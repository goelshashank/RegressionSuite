package com.dhisco.regression.core.interceptors;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 07-04-2019
 */
@Aspect
@Component
@Log4j2
public class LoggingHandler {


	@Pointcut("within(com.dhisco..*)")
	public void withinPackage() {
	}

	@Pointcut("@annotation(com.dhisco.regression.core.LogTime)")
	public void logTimeMethods() {}

	@Pointcut("execution(* *(..))")
	protected void forAllMethods() {
	}

/*	@Before("withinPackage() &&  logTimeMethods() && forAllMethods()")
	public void logBefore(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		log.info("Before " + methodName);
	}

	@After("withinPackage() &&  logTimeMethods() && forAllMethods()")
	public void logAfter(JoinPoint jp) {
		String className=jp.getSignature().getDeclaringType().getSimpleName();
		String methodName = jp.getSignature().getName();
		log.info("Before " + className+"."+methodName);
	}*/

	@Around("withinPackage() &&  logTimeMethods() && forAllMethods()")
	public Object around(ProceedingJoinPoint point) {
		long start = System.currentTimeMillis();
		Object result=null;
		try {
			result = point.proceed();
		} catch (Throwable e){
			log.debug(e.getMessage(),e);
		}
		log.debug(
				"#{}({}) in {}s",point.getSignature().getDeclaringType().getSimpleName(),
				MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
				(System.currentTimeMillis() - start)/1000
		);
		return result;
	}



}
