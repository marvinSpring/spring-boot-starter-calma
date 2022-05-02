package com.marvin.handler;

import com.marvin.event.CalmaEvent;
import com.marvin.model.CalmaExceptionNotice;
import com.marvin.model.CalmaNotice;
import com.marvin.model.HttpExceptionNotice;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @Describe: 将异常信息发布到应用上下文中
 * @Date: 2021/03/01
 * @Author: Marvin
 */
@Configuration
public class CalmaHandler {//异常调度器

    private final ApplicationEventPublisher applicationEventPublisher;

    private final CalmaExceptionNotice calmaExceptionNotice;

    /**
     *
     * @param applicationEventPublisher 事件发布器
     * @param calmaExceptionNotice 全局异常通知
     */
    public CalmaHandler(ApplicationEventPublisher applicationEventPublisher, CalmaExceptionNotice calmaExceptionNotice) {
        super();
        this.applicationEventPublisher = applicationEventPublisher;
        this.calmaExceptionNotice = calmaExceptionNotice;
    }

    /**
     * 创建常规的异常通知
     * @param objArgs 出现异常的方法中的的参数
     * @param e 被捕获的异常
     * @param projectName 项目名称
     */
    public void createNotice(Object[] objArgs, RuntimeException e, String projectName) {
        CalmaNotice notice = new CalmaNotice(e, objArgs, projectName);
        CalmaEvent event = new CalmaEvent(this, notice);
        applicationEventPublisher.publishEvent(event);//发布事件——这里将事件发布到applicationContext中
    }

    /**
     * 创建mvc版本的异常通知
     * @param ex 被捕获的异常
     * @param url 出现异常的restful URL
     * @param param 该请求携带的参数
     * @param requestBody 该请求携带的Body
     * @param headers 该请求携带的头
     * @param requestMethod 请求的方法名称
     */
    public void createHttpNotice(RuntimeException ex, String url, Map<String, String> param,
                                 String requestBody, Map<String, String> headers,String requestMethod) {
        HttpExceptionNotice httpExceptionNotice = new HttpExceptionNotice(ex, String.join(
                calmaExceptionNotice.getProjectName(), "的异常通知"), url, param, requestBody, headers,requestMethod);
        applicationEventPublisher.publishEvent(new CalmaEvent(this,httpExceptionNotice));
    }
}
