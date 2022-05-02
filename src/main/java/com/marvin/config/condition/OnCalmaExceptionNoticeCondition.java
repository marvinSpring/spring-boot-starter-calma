package com.marvin.config.condition;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;


@Order(10)
@Slf4j
public class OnCalmaExceptionNoticeCondition extends SpringBootCondition{

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		log.info("-----------------》》》》》异常通知开启《《《《《《《-------------------------");
		return ConditionOutcome.match("默认开启通知");
	}
}
