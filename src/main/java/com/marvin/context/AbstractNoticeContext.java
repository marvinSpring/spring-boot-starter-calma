package com.marvin.context;

import com.marvin.context.aware.StatisticallyAware;
import com.marvin.context.support.AbstractConditionPostProcessor;
import com.marvin.context.support.IGExceptionPostProcessor;
import com.marvin.event.ExceptionEvent;

/**
 * @Describe: 异常信息发布的上下文
 * @Date: 2021/03/01
 * @Author: Marvin
 */
public abstract class AbstractNoticeContext implements StatisticallyAware {

    public final void publishEventNotice(ExceptionEvent event) {
        //条件判断植入
        AbstractConditionPostProcessor igExceptionPostProcessor = new IGExceptionPostProcessor(event.getNotice().getE());
        setNextPostProcessor(igExceptionPostProcessor,event);

        if (igExceptionPostProcessor.pass()) {
            doPublishEventNotice(event);
        }
    }

    //hook method , 可以让其他的增强器在此处植入
    public abstract void setNextPostProcessor(AbstractConditionPostProcessor abstractPostProcessor, ExceptionEvent event);

    public abstract void doPublishEventNotice(ExceptionEvent event);

}
