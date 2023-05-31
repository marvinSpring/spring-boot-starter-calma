package com.marvin.context.support;

import java.util.Objects;

/**
 * 后置增强处理器，决定是否具体发通知
 *
 * @author marvin
 */
public abstract class AbstractPostProcessor {


    protected AbstractPostProcessor nextPostProcessor;

    public boolean haveNextPostProcessor() {
        return nextPostProcessor != null;
    }

    public void setNextPostProcessor(AbstractPostProcessor nextPostProcessor) {
        this.nextPostProcessor = nextPostProcessor;
    }

    public boolean pass() {
        return condition() && (Objects.isNull(nextPostProcessor) || nextPostProcessor.pass());
    }

    //是否放行，根据自己的需求决定
    abstract protected boolean condition();

}
