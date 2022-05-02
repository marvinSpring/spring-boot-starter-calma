package com.marvin.event.listener;

import com.marvin.event.CalmaEvent;
import com.marvin.model.loader.SmartExceptionLoader;
import org.springframework.context.ApplicationListener;

import com.marvin.util.component.NoticeSendComponent;

public abstract class AbstractCalmaNotifier implements ApplicationListener<CalmaEvent>{

	private final NoticeSendComponent<SmartExceptionLoader> noticeSendComponent;//发送通知的组件
	
	public AbstractCalmaNotifier(NoticeSendComponent<SmartExceptionLoader> noticeSendComponent) {
		this.noticeSendComponent = noticeSendComponent;//钉钉或者SMS
	}

	@Override
	public void onApplicationEvent(CalmaEvent event) {//CalmaEvent事件发布到applicationContext后,ApplicationListener一旦监听到该事件发布就调用本方法
		send(event.getNotice());
	}
	
	private void send(SmartExceptionLoader notice) {
		noticeSendComponent.send(notice);
	}
}
