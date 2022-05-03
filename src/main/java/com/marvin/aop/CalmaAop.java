package com.marvin.aop;

import com.marvin.anno.CalmaExceptionListener;
import com.marvin.handler.CalmaHandler;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;


@Aspect
/**
 * @Describe:核心，通过Aop中的在方法执行抛异常后切入的这个方式来将异常进行捕获，然后对捕获到的异常进行处理
 * @Date:2021/03/01
 * @Author:Marvin
 */
public class CalmaAop {//在异常出现的时候收集异常创建通知

	private final CalmaHandler handler;
	
	@Value("${spring.application.name}")
	private String projectName;
	
	public CalmaAop(CalmaHandler handler) {
		this.handler = handler;
	}

	@AfterThrowing(value = "@within(listener)",throwing = "e",argNames = "listener,e")//创建通知
	public void classExceptionNotifier(JoinPoint joinPoint, CalmaExceptionListener listener , RuntimeException e) {
		handler.createNotice(joinPoint.getArgs(),e,projectName);
	}

	@AfterThrowing(value = "@annotation(listener)",throwing = "e",argNames = "listener,e")//创建通知
	public void methodExceptionNotifier(JoinPoint joinPoint, CalmaExceptionListener listener , RuntimeException e) {
		handler.createNotice(joinPoint.getArgs(),e,projectName);
	}
	
}
