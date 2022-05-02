package com.marvin.anno;

import com.marvin.aop.CalmaAop;
import com.marvin.web.CalmaExceptionHandlerResolver;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.method.HandlerMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.*;

/**
 * 异常监听注解，用来监听这个注解标注的类所产生的运行时异常
 *
 * 异常监听有俩种模式：
 * 1.COMMON 普通程序
 * 本模式下分俩中捕获方式
 *     1.1通过标注本注解在类中捕获{@link CalmaAop#classExceptionNotifier(JoinPoint, CalmaExceptionListener, RuntimeException)}
 *     1.2通过标注本注解在方法中捕获{@link CalmaAop#methodExceptionNotifier(JoinPoint, CalmaExceptionListener, RuntimeException)}
 *
 * 2.WEB web模式
 * 本模式下捕获web请求方法handler中的异常，该handler通常为您写的处理器（标注@Controller/@RestController）\
 * {@link CalmaExceptionHandlerResolver#resolveException(HttpServletRequest, HttpServletResponse, Object, Exception)}
 *
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CalmaExceptionListener {

}
