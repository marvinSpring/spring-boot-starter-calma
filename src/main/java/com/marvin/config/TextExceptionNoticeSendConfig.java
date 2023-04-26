package com.marvin.config;

import com.marvin.anno.ConditionOnTextExceptionNotice;
import com.marvin.model.CalmaNotice;
import com.marvin.util.CalmaValueResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionOnCalmaExceptionNotice
@ConditionOnTextExceptionNotice
@ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
public class TextExceptionNoticeSendConfig {
	
	@Bean
	@ConditionalOnMissingBean
	public CalmaValueResolver exceptionNotice() {
		return CalmaNotice::createText;
	}
}