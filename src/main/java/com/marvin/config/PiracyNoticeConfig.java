package com.marvin.config;

import com.marvin.anno.ConditionOnPiracyExceptionNotice;
import com.marvin.listener.AbstractPiracyNotifier;
import com.marvin.listener.PiracyNotifier;
import com.marvin.util.NoticeSendComponent;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnPiracyExceptionNotice
public class PiracyNoticeConfig {

    @Bean
    @ConditionalOnMissingBean
    public AbstractPiracyNotifier abstractPiracyNotifier(NoticeSendComponent noticeSendComponent){
        AbstractPiracyNotifier abstractPiracyNotifier = new PiracyNotifier(noticeSendComponent);
        return abstractPiracyNotifier;
    }
}
