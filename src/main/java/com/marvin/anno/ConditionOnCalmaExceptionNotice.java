package com.marvin.anno;

import com.marvin.config.condition.OnCalmaExceptionNoticeCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;



@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Conditional(value = {OnCalmaExceptionNoticeCondition.class})
public @interface ConditionOnCalmaExceptionNotice {

}
