package com.marvin.handler;

import com.marvin.config.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.event.ExceptionEvent;
import com.marvin.model.notice.CommonNotice;
import com.marvin.model.notice.HttpNotice;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Describe: 将异常信息发布
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Configuration
@ConditionOnCalmaExceptionNotice
public class DispatcherHandler {//异常调度器

    private final ApplicationEventPublisher applicationEventPublisher;

    @Value("${calma.exceptionnotice.exproject-name:${spring.application.name:project}}")
    private String projectName;

    public DispatcherHandler(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void createNotice(Object[] objArgs, RuntimeException e, String projectName) {
        CommonNotice notice = new CommonNotice(e, objArgs, projectName);
        applicationEventPublisher.publishEvent(new ExceptionEvent(this, notice));//发布事件——这里将事件发布到applicationContext中
    }

    public HttpNotice createHttpNotice(RuntimeException ex, String url, Map<String, String> param,
                                       String requestBody, Map<String, String> headers, String requestMethod) {
        HttpNotice httpExceptionNotice = new HttpNotice(ex, String.join(
                projectName, "的异常通知"), url, param, requestBody, headers,requestMethod);
        applicationEventPublisher.publishEvent(new ExceptionEvent(this,httpExceptionNotice));
        return httpExceptionNotice;
    }
}
