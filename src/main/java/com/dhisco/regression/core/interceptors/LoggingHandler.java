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
/*


	@Pointcut("within(com.dhisco..*)")
	public void components() {
	}

	@Pointcut("@annotation(com.dhisco.regression.core.LogTime)")
	public void logTimeMethods() {}

	@Pointcut("execution(public * *(..))")
	protected void allMethods() {
	}

	@Before("components()  && logTimeMethods() ")
	public void logBefore(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		log.info("Before " + methodName);
	}

	@After("components()  && logTimeMethods() ")
	public void logAfter(JoinPoint jp) {
		String className=jp.getSignature().getDeclaringType().getSimpleName();
		String methodName = jp.getSignature().getName();
		log.info("Before " + className+"."+methodName);
	}

	@Around("components()  && logTimeMethods() ")
	public Object around(ProceedingJoinPoint point) {
		long start = System.currentTimeMillis();
		Object result=null;
		try {
			 result = point.proceed();
		} catch (Throwable e){
			log.debug(e.getMessage(),e);
		}
		log.info(
				"#{}({}) in {}s",
				MethodSignature.class.cast(point.getSignature()).getMethod().getName(),
				result,
				System.currentTimeMillis() - start
		);
		return result;
	}
*/



}