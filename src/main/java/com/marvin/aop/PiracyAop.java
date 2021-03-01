package com.marvin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.marvin.anno.PiracyExceptionListener;
import com.marvin.handler.PiracyHandler;

@Aspect
@Component
/**
 * @Describe:核心，通过Aop中的在方法执行抛异常后切入的这个方式来将异常进行捕获，然后对捕获到的异常进行处理
 * @Date:2021/03/01
 * @Author:Marvin
 */
public class PiracyAop {//在异常出现的时候收集异常创建通知

	private PiracyHandler handler;
	
	@Value("${spring.application.name}")
	private String projectName;
	
	public PiracyAop(PiracyHandler handler) {
		this.handler = handler;
	}

	@AfterThrowing(value = "@within(listener)",throwing = "e",argNames = "joinPoint,listener,e")//创建通知
	public void piracyExceptionNotifier(JoinPoint joinPoint,PiracyExceptionListener listener ,RuntimeException e) {
		handler.createNotice(joinPoint.getArgs(),e,projectName);
	}
	
}
