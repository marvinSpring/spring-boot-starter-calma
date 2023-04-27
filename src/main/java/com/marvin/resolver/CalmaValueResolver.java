package com.marvin.resolver;

import com.marvin.model.notice.CommonNotice;

@FunctionalInterface
public interface CalmaValueResolver<T extends CommonNotice>  {

    public String resolve(T exceptionNotice);

}
