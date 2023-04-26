package com.marvin.config.notice;

//import com.marvin.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.aop.CalmaAop;
import com.marvin.handler.CalmaHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConditionOnCalmaExceptionNotice
//@ConditionalOnProperty(prefix = "Calma", value = "exceptionnotice.enbaled", matchIfMissing = true)
@ConditionalOnProperty(name = "calma.exceptionnotice.listen-type", havingValue = "COMMON", matchIfMissing = true)
@Slf4j
public class AopConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "calma.exceptionnotice",name = "enabled",havingValue = "true")
    public CalmaAop calmaAop(CalmaHandler calmaHandler ){
        if (log.isDebugEnabled()){
            log.info("-----------------》》》》》普通通知模式开启《《《《《《《-------------------------");
        }
        return new CalmaAop(calmaHandler);
    }

}
