package com.marvin.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import com.marvin.event.PiracyListEvent;
import com.marvin.model.PiracyNotice;

@Component
public class PiracyHandler {//异常调度器

	private final ApplicationEventPublisher applicationEventPublisher;
	
	public PiracyHandler(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public ApplicationEventPublisher getApplicationEventPublisher() {
		return applicationEventPublisher;
	}

	public void createNotice( Object[] objArgs, RuntimeException e,String projectName) {
		PiracyNotice mailNotice = new PiracyNotice(e, objArgs, projectName);
		PiracyListEvent event = new PiracyListEvent(this,mailNotice);
		applicationEventPublisher.publishEvent(event);//发布事件——这里会将事件发布到applicationContext中
	}
	
}
