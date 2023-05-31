package com.marvin.config.aop;

import com.marvin.config.anno.CalmaExceptionListener;
import com.marvin.context.AbstractNoticeContext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;


@Aspect
@Slf4j
/**
 * @Describe:核心，通过Aop中的在方法执行抛异常后切入的这个方式来将异常进行捕获，然后对捕获到的异常进行处理
 * @Date:2021/03/01
 * @Author:Marvin
 */
public class CalmaAop {//在异常出现的时候收集异常创建通知

	private final AbstractNoticeContext noticeContext;
	
	@Value("${spring.application.name}")
	private String projectName;
	
	public CalmaAop(AbstractNoticeContext noticeContext) {
		this.noticeContext = noticeContext;
	}

	@AfterThrowing(value = "@within(listener)",throwing = "e",argNames = "joinPoint,listener,e")//创建通知
	public void classExceptionNotifier(JoinPoint joinPoint, CalmaExceptionListener listener , RuntimeException e) {
		createNotice(joinPoint.getArgs(),e,projectName);
	}

	@AfterThrowing(value = "@annotation(listener)",throwing = "e",argNames = "joinPoint,listener,e")//创建通知
	public void methodExceptionNotifier(JoinPoint joinPoint, CalmaExceptionListener listener , RuntimeException e) {
		createNotice(joinPoint.getArgs(),e,projectName);
	}

	public void createNotice(Object[] objArgs, RuntimeException e, String projectName) {
		noticeContext.createNotice(objArgs,e,projectName);
	}


}
