package com.marvin.config.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

@Order(3)
@Slf4j
public class OnWebAutoConfigureCondition extends SpringBootCondition {

    @Override
    public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String auto = context.getEnvironment().getProperty("calma.exceptionnotice.auto");
        if (!StringUtils.isEmpty(auto) && auto.equalsIgnoreCase("true")) {
            log.info("-------------加载web忽略模块----------------");
            return ConditionOutcome.match(auto);
        }
        return ConditionOutcome.noMatch(auto);
    }
}
