package com.marvin.config.notice;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.listener.AbstractCalmaNotifier;
import com.marvin.listener.CalmaNotifier;
import com.marvin.component.NoticeSendComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnCalmaExceptionNotice
public class CalmaNoticeConfig {

    @Bean
    @ConditionalOnMissingBean
    public AbstractCalmaNotifier abstractCalmaNotifier(NoticeSendComponent noticeSendComponent){
        return new CalmaNotifier(noticeSendComponent);
    }
}
