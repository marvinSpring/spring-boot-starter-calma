package com.marvin.web;

import com.marvin.anno.CalmaExceptionListener;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.IOException;
import java.lang.reflect.Type;

public class DefaultRequestBodyResolver extends RequestBodyAdviceAdapter implements CurrentRequestBodyResolver {

    private final ThreadLocal<String> currentRequestBodyInfo = ThreadLocal.withInitial(() -> "");

    @Override
    public void remove() {
        currentRequestBodyInfo.remove();
    }

    @Override
    public String getRequestBody() {
        return currentRequestBodyInfo.get();
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type tragetType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(CalmaExceptionListener.class) ||
                methodParameter.getContainingClass().isAnnotationPresent(CalmaExceptionListener.class);
    }

    @Override
    public Object afterBodyRead(Object requestBody, HttpInputMessage httpInputMessage,
                                MethodParameter methodParameter, Type targetType,
                                Class<? extends HttpMessageConverter<?>> converterType) {
        StringBuilder sb = new StringBuilder(requestBody.toString());
        String body = "";
        if (sb.length() > 500) {
            body = sb.substring(0, 500) + "...";
        } else {
            body = sb.toString();
        }
        currentRequestBodyInfo.set(body);
        return body;
    }

}
