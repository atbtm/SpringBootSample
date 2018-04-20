package com.atbtm.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class HttpAspect {
	private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
	
	/***	 inefficient intercept
	 * 
	//#1: intercept all methods within PersonController, matching all signature. This action will be exec before method exec.
	@Before("execution(public * com.atbtm.controller.PersonController.*(..))")
	public void logBefore() {
		System.out.println("Intercepted..");
	}
	@After("execution(public * com.atbtm.controller.PersonController.*(..))")
	public void logAfter() {
		System.out.println("Intercepted..After");
	}
	 */
	
	//#2: to reduce repeating exceution condition:
	@Pointcut("execution(public * com.atbtm.controller.PersonController.*(..))")
	public void log() {
	}
	@Before("log()")
	public void doBefore(JoinPoint joinPoint){
		logger.info("Logged before");
		//get attributes handler
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest req = attributes.getRequest();
		
		//可能需要log的信息：
		
		//url
		logger.info("url={}", req.getRequestURL());
		
		//http method
		logger.info("method={}", req.getMethod());

		//ip
		logger.info("ip={}", req.getRemoteAddr());

		//hostname?
		logger.info("hostname?={}", req.getRemoteHost());

		//class method
		logger.info("class method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());

		//args
		logger.info("args={}", joinPoint.getArgs());
	}
	@After("log()")
	public void doAfter(){
		logger.info("Logged after");
	}
	
	/**
	 *  Intercept http return body
	 */
	@AfterReturning(returning="obj", pointcut="log()")
	public void doAfterReturning(Object obj) {
		logger.info("response={}", obj);
	}
}
