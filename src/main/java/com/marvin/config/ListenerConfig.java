package com.marvin.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.listener.AbstractPiracyNotifier;
import com.marvin.listener.PiracyNotifier;
import com.marvin.model.PiracyNotice;
import com.marvin.util.NoticeSendComponent;

@Configuration
@Slf4j
public class ListenerConfig {

	@Bean
	@ConditionalOnMissingBean
	public PiracyNotifier piracyNotifier( NoticeSendComponent<PiracyNotice> component) {
		log.info("-----------------》》》》》监听开启《《《《《《《-------------------------");
		AbstractPiracyNotifier piracyNotifier = new PiracyNotifier(component);
		return (PiracyNotifier) piracyNotifier;
	}
	
}
