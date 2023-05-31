package com.marvin.event.listener;

import com.marvin.event.ExceptionEvent;
import com.marvin.model.notice.CommonNotice;
import org.springframework.context.ApplicationListener;

import com.marvin.context.support.component.NoticeSendComponent;

public abstract class AbstractCalmaNotifier implements ApplicationListener<ExceptionEvent>{

	private final NoticeSendComponent<CommonNotice> noticeSendComponent;//发送通知的组件
	
	public AbstractCalmaNotifier(NoticeSendComponent<CommonNotice> noticeSendComponent) {
		this.noticeSendComponent = noticeSendComponent;//钉钉或者SMS
	}

	@Override
	public void onApplicationEvent(ExceptionEvent event) {//CalmaEvent事件发布到applicationContext后,ApplicationListener一旦监听到该事件发布就调用本方法
		send(event.getNotice());
	}
	
	private void send(CommonNotice notice) {
		noticeSendComponent.send(notice);
	}
}
