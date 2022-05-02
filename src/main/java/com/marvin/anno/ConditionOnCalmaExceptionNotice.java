package com.marvin.anno;

import com.marvin.config.condition.OnCalmaExceptionNoticeCondition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.*;


/**
 * yml 配置文件中 calma.exceptionnotice.enabled 是true 才开启
 * @see OnCalmaExceptionNoticeCondition#getMatchOutcome(ConditionContext, AnnotatedTypeMetadata)
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Conditional(value = {OnCalmaExceptionNoticeCondition.class})
public @interface ConditionOnCalmaExceptionNotice {

}
