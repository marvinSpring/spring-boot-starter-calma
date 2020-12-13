package com.marvin.config.condition;

import org.springframework.boot.autoconfigure.condition.ConditionOutcome;
import org.springframework.boot.autoconfigure.condition.SpringBootCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotatedTypeMetadata;

import com.marvin.anno.PiracyExceptionListener;

@Order(10)//优先级不需要很高
public class OnPiracyExceptionNoticeCondition extends SpringBootCondition{

	@Override
	public ConditionOutcome getMatchOutcome(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return ConditionOutcome.match("默认开启通知");
	}
}
