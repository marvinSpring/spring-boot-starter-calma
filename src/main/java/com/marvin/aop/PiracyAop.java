package com.marvin.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.marvin.anno.PiracyExceptionListener;
import com.marvin.handler.PiracyHandler;

@Aspect
@Component
public class PiracyAop {

	private PiracyHandler handler;
	
	public PiracyAop(PiracyHandler handler) {
		this.handler = handler;
	}

	@AfterThrowing(value = "@within(listener)",throwing = "e",argNames = "listener,e")////point()point()
	public void mailExceptionNotifier(JoinPoint joinPoint,PiracyExceptionListener listener ,RuntimeException e) {
		handler.createNotice(joinPoint.getSignature().getName(),joinPoint.getArgs(),e);
	}
	
}
