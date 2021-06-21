package com.marvin.anno;

import java.lang.annotation.*;

@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CalmaExceptionListener {//异常监听注解，用来监听这个注解标注的类所产生的运行时异常

}
