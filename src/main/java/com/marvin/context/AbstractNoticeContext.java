package com.marvin.context;

import com.marvin.config.web.load.WebAutoLoadConfig;
import com.marvin.context.support.AbstractPostProcessor;
import com.marvin.context.support.IGAnnoPostProcessor;
import com.marvin.context.support.IGExceptionPostProcessor;
import com.marvin.event.ExceptionEvent;
import com.marvin.model.notice.CommonNotice;
import com.marvin.model.notice.HttpNotice;
import com.marvin.statistic.cache.StatisticHelper;
import com.marvin.util.web.bridge.CacheBridge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Set;

/**
 * @Describe: 将异常信息发布
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public abstract class AbstractNoticeContext {//异常调度器

    @Value("${calma.exceptionnotice.exproject-name:${spring.application.name:project}}")
    private String projectName;

    @Lazy
    @Autowired
    @Resource
    private WebAutoLoadConfig webAutoLoadConfig;

    public CommonNotice createNotice(Object[] objArgs, RuntimeException e, String projectName) {
        CommonNotice notice = new CommonNotice(e, objArgs, projectName);
        statistic(notice);
        publishEvent(new ExceptionEvent(this, notice));//发布事件——这里将事件发布到applicationContext中
        return notice;
    }

    private void statistic(CommonNotice notice) {
        StatisticHelper.common(notice);
        notice.setExceptionStatisticDto(StatisticHelper.get(notice.getE().getClass().getName()));
    }

    public HttpNotice createHttpNotice(RuntimeException ex, String url, Map<String, String> param,
                                       String requestBody, Map<String, String> headers, String requestMethod) {
        HttpNotice httpExceptionNotice = new HttpNotice(ex, String.join(
                projectName, "的异常通知"), url, param, requestBody, headers, requestMethod);
        statistic(httpExceptionNotice);
        publishEvent(new ExceptionEvent(this, httpExceptionNotice));
        //不通知，只统计
        return httpExceptionNotice;
    }

    public final void publishEvent(ExceptionEvent event) {
        CommonNotice notice = event.getNotice();
        AbstractPostProcessor igExceptionPostProcessor = new IGExceptionPostProcessor(event.getNotice().getE());
        AbstractPostProcessor igAnnoPostProcessor = null;
        if (notice instanceof HttpNotice){
            igAnnoPostProcessor = new IGAnnoPostProcessor(((HttpNotice) notice).getUrl(),getNotifiedMethods());
            igExceptionPostProcessor.setNextPostProcessor(igAnnoPostProcessor);
        }

        setNextPostProcessor(igExceptionPostProcessor);

        if (igExceptionPostProcessor.pass()) {
            doPublishEvent(event);
        }
    }

    //扩展点，可以让其他的增强器在此处进来
    public abstract void setNextPostProcessor(AbstractPostProcessor abstractPostProcessor);

    private Set<String> getNotifiedMethods() {
        if (!CacheBridge.init())
            webAutoLoadConfig.init();
        return CacheBridge.getNotifiedMethods();
    }

    public abstract void doPublishEvent(ExceptionEvent event);

}
