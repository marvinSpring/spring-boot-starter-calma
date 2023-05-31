package com.marvin.context.support;

import java.util.Set;

/**
 * 如果本次通知的异常所在类或者分发是由{@link com.marvin.config.anno.WebIGExceptionListener}标注的就忽略不进行通知
 */
public class IGAnnoPostProcessor extends AbstractPostProcessor{

    private final String url;

    private final Set<String> notifiedMethods;

    public IGAnnoPostProcessor(String url, Set<String> notifiedMethods) {
        this.url = url;
        this.notifiedMethods = notifiedMethods;
    }

    @Override
    protected boolean condition() {
        return notifiedMethods.contains(url);
    }
}
