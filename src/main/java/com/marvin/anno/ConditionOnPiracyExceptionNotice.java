package com.marvin.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Conditional;

import com.marvin.config.condition.OnPiracyExceptionNoticeCondition;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Conditional(value = {OnPiracyExceptionNoticeCondition.class})
public @interface ConditionOnPiracyExceptionNotice {

}
