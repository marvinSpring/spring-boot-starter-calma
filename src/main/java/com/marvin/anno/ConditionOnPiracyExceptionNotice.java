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
public @interface ConditionOnPiracyExceptionNotice {//只有当OnPiracyExceptionNoticeCondition文件存在的时候，这个注解标注的类才会被spring加载到，梦需要的东西

}
