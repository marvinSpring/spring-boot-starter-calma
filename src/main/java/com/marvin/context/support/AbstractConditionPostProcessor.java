package com.marvin.context.support;

import java.util.Objects;

/**
 * 后置条件增强处理器，决定是否具体发通知
 *
 * @author marvin
 */
public abstract class AbstractConditionPostProcessor {

    /**
     * 下个校验节点
     */
    protected AbstractConditionPostProcessor nextPostProcessor;

    /**
     * 是否有下个节点
     * @return true 有/false 无
     */
    public boolean haveNextPostProcessor() {
        return nextPostProcessor != null;
    }

    /**
     * 设置下个节点
     * @param nextPostProcessor 下个节点
     */
    public void setNextPostProcessor(AbstractConditionPostProcessor nextPostProcessor) {
        this.nextPostProcessor = nextPostProcessor;
    }

    /**
     * 根据条件判断是否放行
     * @return true 放行/false不放行
     */
    public boolean pass() {
        return condition() && (Objects.isNull(nextPostProcessor) || nextPostProcessor.pass());
    }

    /**
     * hook method 当前节点条件是否放行，根据自己的需求决定
     * @return true 放行/false 不放行
     */
    abstract protected boolean condition();

}
