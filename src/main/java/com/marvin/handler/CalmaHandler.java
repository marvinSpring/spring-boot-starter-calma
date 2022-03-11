package com.marvin.handler;

import com.marvin.event.CalmaEvent;
import com.marvin.model.CalmaExceptionNotice;
import com.marvin.model.CalmaNotice;
import com.marvin.model.HttpExceptionNotice;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Describe: 将异常信息发布
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Configuration
public class CalmaHandler {//异常调度器

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CalmaExceptionNotice calmaExceptionNotice;

    public CalmaHandler(ApplicationEventPublisher applicationEventPublisher, CalmaExceptionNotice calmaExceptionNotice) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.calmaExceptionNotice = calmaExceptionNotice;
    }

    public void createNotice(Object[] objArgs, RuntimeException e, String projectName) {
        CalmaNotice notice = new CalmaNotice(e, objArgs, projectName);
        CalmaEvent event = new CalmaEvent(this, notice);
        applicationEventPublisher.publishEvent(event);//发布事件——这里将事件发布到applicationContext中
    }

    public HttpExceptionNotice createHttpNotice(RuntimeException ex, String url, Map<String, String> param,
                                 String requestBody, Map<String, String> headers,String requestMethod) {
        HttpExceptionNotice httpExceptionNotice = new HttpExceptionNotice(ex, String.join(
                calmaExceptionNotice.getProjectName(), "的异常通知"), url, param, requestBody, headers,requestMethod);
        applicationEventPublisher.publishEvent(new CalmaEvent(this,httpExceptionNotice));
        return httpExceptionNotice;
    }
}
