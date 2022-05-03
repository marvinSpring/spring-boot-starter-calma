package com.marvin.config.condition;

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;


@Order(10)
public class OnCalmaExceptionNoticeCondition extends SpringBootCondition {

    protected final org.apache.commons.logging.Log log = LogFactory.getLog(this.getClass());


    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Boolean enabled = context.getEnvironment().getProperty("calma.exceptionnotice.enabled", Boolean.class);
        if (enabled != null && enabled) {
            log.info("----------------->>>>>异常通知开启<<<<<-------------------------");
            return ConditionOutcome.match("开启通知");
        }
        return ConditionOutcome.noMatch("不开启通知");
    }
}
