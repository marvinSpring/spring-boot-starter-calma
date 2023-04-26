package com.marvin.config;

import com.marvin.anno.ConditionOnMarkdownExceptionNotice;
import com.marvin.model.CalmaNotice;
import com.marvin.util.CalmaValueResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionOnCalmaExceptionNotice
@ConditionOnMarkdownExceptionNotice
@ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
public class MarkdownExceptionNoticeSendConfig {

    @Bean
    @ConditionalOnMissingBean
    public CalmaValueResolver exceptionNotice() {
        return CalmaNotice::createMarkdown;
    }

}
