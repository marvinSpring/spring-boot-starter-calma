package com.marvin.config.notice;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.aop.CalmaAop;
import com.marvin.handler.CalmaHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnCalmaExceptionNotice
@ConditionalOnProperty(prefix = "Calma", value = "exceptionnotice.enbaled", matchIfMissing = true)
public class AopConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CalmaAop calmaAop(CalmaHandler CalmaHandler ){
        return new CalmaAop(CalmaHandler);
    }

}
