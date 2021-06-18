package com.marvin.handler;

import com.marvin.model.HttpExceptionNotice;
import com.marvin.model.PiracyExceptionNotice;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import com.marvin.event.PiracyListEvent;
import com.marvin.model.PiracyNotice;

import java.util.Map;

/**
 * @Describe: 将异常信息发布
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Configuration
public class PiracyHandler {//异常调度器

    private final ApplicationEventPublisher applicationEventPublisher;

    private final PiracyExceptionNotice piracyExceptionNotice;

    public PiracyHandler(ApplicationEventPublisher applicationEventPublisher, PiracyExceptionNotice piracyExceptionNotice) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.piracyExceptionNotice = piracyExceptionNotice;
    }

    public void createNotice(Object[] objArgs, RuntimeException e, String projectName) {
        PiracyNotice notice = new PiracyNotice(e, objArgs, projectName);
        PiracyListEvent event = new PiracyListEvent(this, notice);
        applicationEventPublisher.publishEvent(event);//发布事件——这里将事件发布到applicationContext中
    }

    public HttpExceptionNotice createHttpNotice(RuntimeException ex, String url, Map<String, String> param,
                                 String requestBody, Map<String, String> headers) {
        HttpExceptionNotice httpExceptionNotice = new HttpExceptionNotice(ex, String.join(
                piracyExceptionNotice.getProjectName(), "的异常通知"), url, param, requestBody, headers);
        applicationEventPublisher.publishEvent(new PiracyListEvent(this,httpExceptionNotice));
        return httpExceptionNotice;
    }
}
