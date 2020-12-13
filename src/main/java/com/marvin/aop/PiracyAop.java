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
public class PiracyAop {//在异常出现的时候收集异常创建通知——梦开始的地方

	private PiracyHandler handler;
	
	@Value("${piracy.projectName}")
	private String projectName = "1";
	
	public PiracyAop(PiracyHandler handler) {
		this.handler = handler;
	}

	@AfterThrowing(value = "@within(listener)",throwing = "e",argNames = "listener,e")//创建通知
	public void piracyExceptionNotifier(JoinPoint joinPoint,PiracyExceptionListener listener ,RuntimeException e) {
		handler.createNotice(joinPoint.getArgs(),e,projectName);
	}
	
}
