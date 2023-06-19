package com.marvin.config.notice;

import com.marvin.config.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.event.listener.AbstractCalmaNotifier;
import com.marvin.event.listener.CalmaNotifier;
import com.marvin.context.support.component.NoticeSendComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnCalmaExceptionNotice
public class CalmaNoticeConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public AbstractCalmaNotifier abstractCalmaNotifier(NoticeSendComponent noticeSendComponent){
        return new CalmaNotifier(noticeSendComponent);
    }
}
