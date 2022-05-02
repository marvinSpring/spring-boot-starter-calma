package com.marvin.config.notice;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.event.listener.AbstractCalmaNotifier;
import com.marvin.event.listener.CalmaNotifier;
import com.marvin.model.Notice;
import com.marvin.util.NoticeSendComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnCalmaExceptionNotice
@ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.enbaled", matchIfMissing = true,havingValue = "true")
public class CalmaNoticeConfig {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "calma", value = "exceptionnotice.enbaled", matchIfMissing = true,havingValue = "true")
    public AbstractCalmaNotifier abstractCalmaNotifier(NoticeSendComponent noticeSendComponent){
        AbstractCalmaNotifier abstractCalmaNotifier = new CalmaNotifier(noticeSendComponent);
        return abstractCalmaNotifier;
    }
}
