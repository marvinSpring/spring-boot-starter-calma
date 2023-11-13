package com.marvin.context;

import com.marvin.config.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.context.support.AbstractConditionPostProcessor;
import com.marvin.event.ExceptionEvent;
import com.marvin.model.notice.CommonNotice;
import com.marvin.statistic.cache.StatisticHelper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

/**
 * 默认通知上下午
 * @author marvin
 */
@Configuration
@ConditionOnCalmaExceptionNotice
public class DefaultNoticeContext extends AbstractNoticeContext {

    private final ApplicationEventPublisher applicationEventPublisher;

    public DefaultNoticeContext(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public CommonNotice createNotice(Object[] objArgs, RuntimeException e, String projectName) {
        CommonNotice notice = new CommonNotice(e, objArgs, projectName);
        statistic(notice);
        publishEventNotice(new ExceptionEvent(this, notice));
        return notice;
    }

    @Override
    public void statistic(CommonNotice notice) {
        StatisticHelper.common(notice);
    }

    @Override
    public void setNextPostProcessor(AbstractConditionPostProcessor abstractPostProcessor, ExceptionEvent event) {
        //something to do,but at there just ok
    }

    @Override
    public void doPublishEventNotice(ExceptionEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
