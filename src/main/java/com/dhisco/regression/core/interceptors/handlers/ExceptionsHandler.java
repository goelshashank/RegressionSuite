package com.dhisco.regression.core.interceptors.handlers;

import com.dhisco.regression.core.exceptions.P2DRSException;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author Shashank Goel
 * @version 1.0
 * @since 08-04-2019
 */
@Component
@Aspect
public class ExceptionsHandler {
	@AfterThrowing(pointcut = "target(com.dhisco.regression.core.interceptors.ExceptionInterceptor)" , throwing ="exception")
	public void handleException(JoinPoint joinPoint, P2DRSException exception) {
		Logger log= LogManager.getLogger(joinPoint.getSignature().getDeclaringType());
		log.error("An exception has been thrown in {}.{}",
				joinPoint.getSignature().getDeclaringType().getSimpleName(),joinPoint.getSignature().getName());
		log.error("Cause : " + exception.getCause());

	}
}
