package com.marvin.config.notice;

import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.aop.CalmaAop;
import com.marvin.handler.CalmaHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionOnCalmaExceptionNotice
@ConditionalOnProperty(name = "calma.exceptionnotice.listen-type", havingValue = "COMMON", matchIfMissing = true)
public class AopConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public CalmaAop calmaAop(CalmaHandler calmaHandler ){
        return new CalmaAop(calmaHandler);
    }

}
