package com.marvin.web;

import com.marvin.anno.PiracyExceptionListener;
import com.marvin.handler.PiracyHandler;
import com.marvin.model.PiracyExceptionNotice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PiracyExceptionHandlerResolver implements HandlerExceptionResolver {

    private PiracyHandler piracyHandler;

    private PiracyExceptionNotice piracyExceptionNotice;

    private CurrentRequestHeaderResolver currentRequestHeaderResolver;

    private CurrentRequestBodyResolver currentRequestBodyResolver;

    public PiracyExceptionHandlerResolver(PiracyHandler piracyHandler,
                                          PiracyExceptionNotice piracyExceptionNotice,
                                          CurrentRequestHeaderResolver currentRequestHeaderResolver,
                                          CurrentRequestBodyResolver currentRequestBodyResolver) {
        this.piracyHandler = piracyHandler;
        this.piracyExceptionNotice = piracyExceptionNotice;
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
        PiracyExceptionListener listener = getListener(handlerMethod);
        //创建通知
        if (listener != null && handlerMethod != null && e != null) {
            piracyHandler.createHttpNotice(e, request.getRequestURI(), getParams(request), getRequestBody(), getHeader(request));
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

    private PiracyExceptionListener getListener(HandlerMethod handlerMethod) {
        PiracyExceptionListener listener = handlerMethod.getMethodAnnotation(PiracyExceptionListener.class);
        if (listener == null) {
            listener = handlerMethod.getBeanType().getAnnotation(PiracyExceptionListener.class);
        }
        return listener;
    }
}
