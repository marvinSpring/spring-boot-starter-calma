package com.marvin.config.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;


@Order(0)
@Slf4j
public class OnCalmaExceptionNoticeCondition extends SpringBootCondition{

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String msgType = context.getEnvironment().getProperty("calma.exceptionnotice.enabled");
		if (!StringUtils.isEmpty(msgType) && msgType.equalsIgnoreCase("true")) {
			log.info("-------------Calma异常通知启动----------------");
			return ConditionOutcome.match(msgType);
		}
		return ConditionOutcome.noMatch("calma.exceptionnotice.enabled is false");
	}
}
