package com.marvin.listener;

import com.marvin.event.CalmaEvent;
import com.marvin.model.CalmaNotice;
import org.springframework.context.ApplicationListener;

import com.marvin.util.NoticeSendComponent;

public abstract class AbstractCalmaNotifier implements ApplicationListener<CalmaEvent>{

	private final NoticeSendComponent<CalmaNotice> noticeSendComponent;//发送通知的组件
	
	public AbstractCalmaNotifier(NoticeSendComponent<CalmaNotice> noticeSendComponent) {
		this.noticeSendComponent = noticeSendComponent;//钉钉或者SMS
	}

	@Override
	public void onApplicationEvent(CalmaEvent event) {//CalmaEvent事件发布到applicationContext后,ApplicationListener一旦监听到该事件发布就调用本方法
		send(event.getNotice());
	}
	
	private void send(CalmaNotice notice) {
		noticeSendComponent.send(notice);
	}
}
