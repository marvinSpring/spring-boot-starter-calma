package com.marvin.web.interceptor;

import com.marvin.web.resolver.CurrentRequestBodyResolver;
import lombok.Data;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Data
public class ClearBodyInterceptor implements HandlerInterceptor {

    private CurrentRequestBodyResolver currentRequestBodyResolver;

    public ClearBodyInterceptor(CurrentRequestBodyResolver currentRequestBodyResolver) {
        this.currentRequestBodyResolver = currentRequestBodyResolver;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        currentRequestBodyResolver.remove();
    }
}
