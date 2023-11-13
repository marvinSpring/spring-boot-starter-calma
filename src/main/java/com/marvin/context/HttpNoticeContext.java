package com.marvin.context;

import com.marvin.config.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.config.web.load.WebAutoLoadConfiguration;
import com.marvin.context.support.AbstractConditionPostProcessor;
import com.marvin.context.support.IGAnnoPostProcessor;
import com.marvin.event.ExceptionEvent;
import com.marvin.model.notice.CommonNotice;
import com.marvin.model.notice.HttpNotice;
import com.marvin.model.web.WebNoticeInfo;
import com.marvin.statistic.cache.StatisticHelper;
import com.marvin.util.web.bridge.CacheBridge;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Configuration
@ConditionOnCalmaExceptionNotice
@ConditionalOnProperty(name = "calma.exceptionnotice.listen-type", havingValue = "WEB")
public class HttpNoticeContext extends AbstractNoticeContext implements InitializingBean {

    @Value("${calma.exceptionnotice.exproject-name:${spring.application.name:project}}")
    private String projectName;

    private boolean auto;

    @Autowired(required = false)
    private WebNoticeInfo noticeInfo;

    @Lazy
    @Autowired
    @Resource
    private WebAutoLoadConfiguration webAutoLoadConfiguration;

    private final ApplicationEventPublisher applicationEventPublisher;

    @SuppressWarnings("all")
    public HttpNoticeContext(ApplicationEventPublisher publisher, WebNoticeInfo noticeInfo) {
        this.noticeInfo = noticeInfo;
        this.applicationEventPublisher = publisher;
    }

    @Override
    public void statistic(CommonNotice notice) {
        //http通知方式统计
        StatisticHelper.common(notice);
    }

    public HttpNotice createNotice(RuntimeException ex, String url, Map<String, String> param,
                                   String requestBody, Map<String, String> headers, String requestMethod) {
        HttpNotice httpExceptionNotice = new HttpNotice(ex, String.join(
                projectName, "的异常通知"), url, param, requestBody, headers, requestMethod);
        statistic(httpExceptionNotice);
        publishEventNotice(new ExceptionEvent(this, httpExceptionNotice));
        //不通知，只统计
        return httpExceptionNotice;
    }

    @Override
    public void setNextPostProcessor(AbstractConditionPostProcessor conditionPostProcessor, ExceptionEvent event) {
        AbstractConditionPostProcessor igAnnoPostProcessor = null;
        igAnnoPostProcessor = new IGAnnoPostProcessor(((HttpNotice) event.getNotice()).getUrl(),getNotifiedMethods());
        conditionPostProcessor.setNextPostProcessor(igAnnoPostProcessor);
    }

    @Override
    public void doPublishEventNotice(ExceptionEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public boolean isAuto() {
        return auto;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.auto = Objects.nonNull(this.noticeInfo) && this.noticeInfo.isAuto();
    }

    private Set<String> getNotifiedMethods() {
        if (!CacheBridge.init()) {
            webAutoLoadConfiguration.init();
        }
        return CacheBridge.getNotifiedMethods();
    }
}
