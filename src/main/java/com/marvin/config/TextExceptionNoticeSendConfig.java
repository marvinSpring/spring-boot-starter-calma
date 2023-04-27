package com.marvin.config;

import com.marvin.config.anno.ConditionOnTextExceptionNotice;
import com.marvin.model.notice.CommonNotice;
import com.marvin.resolver.CalmaValueResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnTextExceptionNotice
@ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
public class TextExceptionNoticeSendConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public CalmaValueResolver exceptionNotice() {
		return CommonNotice::createText;
	}
}
