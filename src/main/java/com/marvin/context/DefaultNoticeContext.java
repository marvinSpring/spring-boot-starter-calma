package com.marvin.context;

import com.marvin.config.anno.ConditionOnCalmaExceptionNotice;
import com.marvin.context.support.AbstractPostProcessor;
import com.marvin.event.ExceptionEvent;
import com.marvin.model.web.NoticeInfo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
@ConditionOnCalmaExceptionNotice
public class DefaultNoticeContext extends AbstractNoticeContext implements InitializingBean {

    private final ApplicationEventPublisher applicationEventPublisher;

    private final NoticeInfo noticeInfo;

    private boolean auto;

    public DefaultNoticeContext(ApplicationEventPublisher applicationEventPublisher, NoticeInfo noticeInfo) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.noticeInfo = noticeInfo;
    }

    @Override
    public void setNextPostProcessor(AbstractPostProcessor abstractPostProcessor) {
        //something to do,but at there just ok
    }

    @Override
    public void doPublishEvent(ExceptionEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

    public boolean isAuto() {
        return auto;
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        this.auto = Objects.nonNull(this.noticeInfo) && this.noticeInfo.isAuto();
    }
}
