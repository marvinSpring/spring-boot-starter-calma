package com.marvin.util;

import com.marvin.model.CalmaNotice;

@FunctionalInterface
public interface CalmaValueResolver<T extends CalmaNotice>  {

    public String resolve(T exceptionNotice);

}