package com.marvin.context.support;

import com.marvin.common.exception.IGException;

/**
 * 如果本次通知的异常是{@link IGException}就忽略不进行通知
 */
public class IGExceptionPostProcessor extends AbstractPostProcessor{

    private final Throwable throwable;

    public IGExceptionPostProcessor(Throwable ex) {
        this.throwable = ex;
    }

    @Override
    protected boolean condition() {
        boolean b = throwable instanceof IGException;
        return !b;
    }

}
