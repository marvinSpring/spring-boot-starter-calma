package com.marvin.web.resolver;

import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

public interface CurrentRequestBodyResolver extends RequestBodyAdvice {

    default String getRequestBody() {
        return "";
    }

    void remove();
}
