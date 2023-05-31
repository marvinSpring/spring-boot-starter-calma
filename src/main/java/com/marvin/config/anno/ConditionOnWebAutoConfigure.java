package com.marvin.config.anno;

import com.marvin.config.condition.OnWebAutoConfigureCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
@Conditional(value = {OnWebAutoConfigureCondition.class})
public @interface ConditionOnWebAutoConfigure {

}
