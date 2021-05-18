package com.marvin.anno;

import java.lang.annotation.Target;

import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PiracyExceptionListener {//异常监听注解，用来监听这个注解标注的类所产生的运行时异常

}
