package com.marvin.config.web.load;

import com.marvin.config.anno.ConditionOnWebAutoConfigure;
import com.marvin.config.anno.WebIGExceptionListener;
import com.marvin.model.web.NoticeInfo;
import com.marvin.util.web.bridge.CacheBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Configuration
@EnableConfigurationProperties(NoticeInfo.class)
//@ConditionOnWebAutoConfigure
public class WebAutoLoadConfig {

    @Autowired
    private WebApplicationContext webApplicationContext;

    public void init(){
        RequestMappingHandlerMapping requestMappingHandlerMapping =
                webApplicationContext.getBean(RequestMappingHandlerMapping.class);
        Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();

        //忽略IGExceptionListener注解加在方法上的的请求
        handlerMethods.forEach((path, method) -> {
            WebIGExceptionListener declaredAnnotation = method.getBeanType().getDeclaredAnnotation(WebIGExceptionListener.class);
            if (declaredAnnotation == null && method.getMethodAnnotation(WebIGExceptionListener.class) == null) {
                CacheBridge.addAll(new HashSet<>(path.getPatternsCondition().getPatterns()));
            }
        });
    }

}
