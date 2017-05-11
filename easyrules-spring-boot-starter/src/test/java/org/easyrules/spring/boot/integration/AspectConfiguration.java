package org.easyrules.spring.boot.integration;

import javax.annotation.PostConstruct;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class AspectConfiguration {

	@Aspect
	@Component
	public class LogAspect {

		final Logger logger = LoggerFactory.getLogger(LogAspect.class);

		@PostConstruct
		public void init() {
			logger.info("log aspect initialized");
		}

		@Pointcut ("!@target(org.springframework.context.annotation.Configuration)")
		public void springConfigurationAnnotations() {
			// all NoLog annotation methods
		}

		@Pointcut ("execution(* org.easyrules.spring.boot.integration..*..*(..))")
		public void allMethods() {
			// all NoLog annotation methods
		}

		@Pointcut ("!execution(* org.easyrules.spring.boot.integration..*..*Bean.get*(..))")
		public void allGetMethods() {
			// all get methods
		}

		@Pointcut ("!execution(* org.easyrules.spring.boot.integration..*..*Bean.is*(..))")
		public void allIsMethods() {
			// all is methods
		}

		@Pointcut ("!execution(* org.easyrules.spring.boot.integration..*..*Bean.set*(..))")
		public void allSetMethods() {
			// all set methods
		}

		@Around (value = "allMethods() && springConfigurationAnnotations() && allGetMethods() && allIsMethods() ", argNames = "joinPoint")
		public Object aroundScreenBeansOfAydes(ProceedingJoinPoint joinPoint) throws Throwable {
			MethodSignature signature = (MethodSignature) joinPoint.getSignature();

			Object returnValue;
			long endTime;
			long startTime;

			startTime = System.nanoTime();
			returnValue = joinPoint.proceed();
			endTime = System.nanoTime();

			logger.info(signature.toString() + " - duration " + (endTime - startTime) / 1000000);

			return returnValue;
		}
	}
}
