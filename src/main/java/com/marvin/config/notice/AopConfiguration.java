package com.marvin.config.notice;

import com.marvin.config.aop.CalmaAop;
import com.marvin.context.AbstractNoticeContext;
import com.marvin.context.DefaultNoticeContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "calma.exceptionnotice.listen-type", havingValue = "COMMON", matchIfMissing = true)
@Slf4j
public class AopConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
    public CalmaAop calmaAop(DefaultNoticeContext dispatcherHandler ){
        if (log.isDebugEnabled()){
            log.info("-----------------》》》》》普通通知模式开启《《《《《《《-------------------------");
        }
        return new CalmaAop(dispatcherHandler);
    }

}
