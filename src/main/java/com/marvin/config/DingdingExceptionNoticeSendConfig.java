package com.marvin.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.marvin.anno.ConditionOnPiracyExceptionNotice;
import com.marvin.model.PiracyNotice;
import com.marvin.util.PiracyNoticeTextResolver;

@Configuration
@ConditionOnPiracyExceptionNotice
public class DingdingExceptionNoticeSendConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public PiracyNoticeTextResolver<PiracyNotice> exceptionNotice() {
		return x->x.createText();
	}
}
