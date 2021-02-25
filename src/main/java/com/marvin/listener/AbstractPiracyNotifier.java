package com.marvin.listener;

import org.springframework.context.ApplicationListener;

import com.marvin.event.PiracyListEvent;
import com.marvin.model.PiracyNotice;
import com.marvin.util.Client;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.sms.SendSmsClient;

public abstract class AbstractPiracyNotifier implements ApplicationListener<PiracyListEvent>{

	
	private final NoticeSendComponent<PiracyNotice> noticeSendComponent;//发送通知的组件
	
	public AbstractPiracyNotifier(NoticeSendComponent<PiracyNotice> noticeSendComponent) {
		this.noticeSendComponent = noticeSendComponent;//钉钉或者SMS
	}

	@Override
	public void onApplicationEvent(PiracyListEvent event) {//PiracyListEvent事件发布到applicationContext后,ApplicationListener一旦监听到该事件发布就调用本方法
		if(event instanceof PiracyListEvent) {
			send(event.getNotice());
		}
	}
	
	private void send(PiracyNotice notice) {
		noticeSendComponent.send(notice);
	}
}
