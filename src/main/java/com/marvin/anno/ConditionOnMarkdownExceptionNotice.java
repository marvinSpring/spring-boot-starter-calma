package com.marvin.anno;

import com.marvin.config.condition.OnMarkdownExceptionNoticeCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Conditional(value = {OnMarkdownExceptionNoticeCondition.class})
public @interface ConditionOnMarkdownExceptionNotice {

}

