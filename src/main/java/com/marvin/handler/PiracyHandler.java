package com.marvin.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import com.marvin.event.PiracyListEvent;
import com.marvin.model.PiracyNotice;

/**
 * @Describe: 将异常信息发布
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Configuration
public class PiracyHandler {//异常调度器

	private final ApplicationEventPublisher applicationEventPublisher;
	
	public PiracyHandler(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void createNotice( Object[] objArgs, RuntimeException e,String projectName) {
		PiracyNotice notice = new PiracyNotice(e, objArgs, projectName);
		PiracyListEvent event = new PiracyListEvent(this,notice);
		applicationEventPublisher.publishEvent(event);//发布事件——这里将事件发布到applicationContext中
	}
	
}
