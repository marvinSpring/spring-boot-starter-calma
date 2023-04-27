package com.marvin.config.anno;

import com.marvin.config.condition.OnTextExceptionNoticeCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Conditional(value = {OnTextExceptionNoticeCondition.class})
public @interface ConditionOnTextExceptionNotice {

}
