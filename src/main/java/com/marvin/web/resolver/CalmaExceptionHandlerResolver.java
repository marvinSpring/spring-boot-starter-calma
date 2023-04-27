package com.marvin.web.resolver;

import com.marvin.common.enumeration.ExceptionType;
import com.marvin.common.exception.NoSuchHttpRequestMethodException;
import com.marvin.config.anno.CalmaExceptionListener;
import com.marvin.handler.DispatcherHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.marvin.common.enumeration.ExceptionType.UNABLE_FIND_CONTROLLER;


public class CalmaExceptionHandlerResolver implements HandlerExceptionResolver {

    private final DispatcherHandler dispatcherHandler;

    private final CurrentRequestHeaderResolver currentRequestHeaderResolver;

    private final CurrentRequestBodyResolver currentRequestBodyResolver;

    public CalmaExceptionHandlerResolver(DispatcherHandler dispatcherHandler,
                                         CurrentRequestHeaderResolver currentRequestHeaderResolver,
                                         CurrentRequestBodyResolver currentRequestBodyResolver) {
        this.dispatcherHandler = dispatcherHandler;
        this.currentRequestHeaderResolver = currentRequestHeaderResolver;
        this.currentRequestBodyResolver = currentRequestBodyResolver;
    }

    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        RuntimeException e = null;
        if (ex instanceof RuntimeException) {
            e = (RuntimeException) ex;
        }
        HandlerMethod handlerMethod = null;
        if (handler instanceof HandlerMethod) {
            handlerMethod = (HandlerMethod) handler;
        }
        CalmaExceptionListener listener = getListener(handlerMethod);
        //创建通知
        if (listener != null && e != null) {
            dispatcherHandler.createHttpNotice(e, request.getRequestURI(), getParams(request), getRequestBody(), getHeader(request), getMethod(request));
        }
        return null;
    }

    private Map<String, String> getHeader(HttpServletRequest request) {
        return currentRequestHeaderResolver.headers(request);
    }

    private String getRequestBody() {
        return currentRequestBodyResolver.getRequestBody();
    }

    private Map<String, String> getParams(HttpServletRequest request) {
        Map<String, String> headers = new HashMap<>();
        request.getParameterMap().forEach((x, y) ->
                headers.put(x, String.join(" , ", Arrays.asList(y)))
        );
        return headers;
    }

    private String getMethod(HttpServletRequest request) {
        String method;
        try {
            method = request.getMethod();
        } catch (Exception e) {
            throw new NoSuchHttpRequestMethodException(ExceptionType.ERROR_OBTAINING_METHOD, e);
        }
        return method;
    }

    private CalmaExceptionListener getListener(HandlerMethod handlerMethod) {
        if (Objects.isNull(handlerMethod)) {
            throw new NoSuchHttpRequestMethodException(UNABLE_FIND_CONTROLLER);
        }
        //获取请求方法上的注解
        CalmaExceptionListener listener = handlerMethod.getMethodAnnotation(CalmaExceptionListener.class);
        //如果请求方法上获取不到，那么去看看是不是在类级别
        if (Objects.isNull(listener)) {
            listener = handlerMethod.getBeanType().getAnnotation(CalmaExceptionListener.class);
        }
        return listener;
    }
}
