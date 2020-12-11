package com.marvin.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

import com.marvin.event.PiracyListEvent;
import com.marvin.model.DingdingNotice;
import com.marvin.model.DingdingResult;
import com.marvin.model.PiracyNotice;
import com.marvin.util.DefaultDingdingClient;
import com.marvin.util.DingNoticeSendComponent;
import com.marvin.util.DingdingClient;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;

public class PiracyNotifier implements ApplicationListener<PiracyListEvent>{
	
	private final NoticeSendComponent<PiracyNotice> dingNoticeSendComponent;
	private PiracyNoticeTextResolver<PiracyNotice> resolver  = new PiracyNoticeTextResolver<PiracyNotice>() {
		
		@Override
		public String resolve(PiracyNotice exceptionNotice) {
			String createText = exceptionNotice.createText();
			return createText;
		}
	};
	private DingdingClient client = new DefaultDingdingClient();

	@SuppressWarnings("unchecked")
	public PiracyNotifier(NoticeSendComponent<PiracyNotice> dingNoticeSendComponent) {
		this.dingNoticeSendComponent = new DingNoticeSendComponent<PiracyNotice>(resolver, client);
	}
	
	@Override
	public void onApplicationEvent(PiracyListEvent event) {
		if(event instanceof PiracyListEvent) {
			send(event.getNotice());
		}
	}
	private void send(PiracyNotice notice) {
		dingNoticeSendComponent.send(notice);
	}
}
