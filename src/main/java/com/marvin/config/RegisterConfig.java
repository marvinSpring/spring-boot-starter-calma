package com.marvin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.listener.PiracyNotifier;
import com.marvin.model.PiracyNotice;
import com.marvin.util.DingNoticeSendComponent;
import com.marvin.util.DingdingClient;
import com.marvin.util.NoticeSendComponent;
import com.marvin.util.PiracyNoticeTextResolver;

@Configuration//注入钉钉通知的组件和异常通知的监听器
public class RegisterConfig {

	private NoticeSendComponent<PiracyNotice> sendComponent;
	
	@Bean
	public NoticeSendComponent<PiracyNotice> registerSendComponent(PiracyNoticeTextResolver<PiracyNotice> resolver,DingdingClient client){
		NoticeSendComponent<PiracyNotice> dingNoticeSendComponent = new DingNoticeSendComponent<PiracyNotice>(resolver, client);
		return dingNoticeSendComponent;
	}
	
	@Bean
	public PiracyNotifier registerEmailNotifier() {
		return new PiracyNotifier(sendComponent);
	}
}
