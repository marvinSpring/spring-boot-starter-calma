package com.marvin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.listener.AbstractPiracyNotifier;
import com.marvin.listener.PiracyNotifier;
import com.marvin.model.PiracyNotice;
import com.marvin.util.NoticeSendComponent;

@Configuration
public class ListenerConfig {

	@Bean
	public PiracyNotifier piracyNotifier(NoticeSendComponent<PiracyNotice> component) {
		AbstractPiracyNotifier piracyNotifier = new PiracyNotifier(component);
		return (PiracyNotifier) piracyNotifier;
	}
	
}
