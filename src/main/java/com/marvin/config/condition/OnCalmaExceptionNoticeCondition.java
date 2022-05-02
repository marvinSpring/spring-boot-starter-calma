package com.marvin.config.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionMessage;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;


@Order(10)
@Slf4j
public class OnCalmaExceptionNoticeCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Boolean enabled = context.getEnvironment().getProperty("calma.exceptionnotice.enabled", Boolean.class);
        if (enabled != null && enabled) {
            log.info("-----------------》》》》》异常通知开启《《《《《《《-------------------------");
            return ConditionOutcome.match("开启通知");
        }
        return ConditionOutcome.noMatch("不开启通知");
    }
}
