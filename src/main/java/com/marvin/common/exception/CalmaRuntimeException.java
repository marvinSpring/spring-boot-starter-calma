package com.marvin.common.exception;


import com.marvin.common.enumeration.ExceptionType;

import java.io.Serializable;

//本stater的内部异常，用来处理内部问题，在别的项目引用本项目时，可能会有本项目报错的问题，这个时候它就不能很好的吧问题抛出来，我在想这种时候怎么能吧问题让开发者知道，再去套娃抛出来还是
public class CalmaRuntimeException extends RuntimeException implements Serializable {

    private static final Long serializableId = 0L;

    public CalmaRuntimeException(ExceptionType type, Throwable throwable) {
        super(type.getMessage(),throwable);
    }

    public CalmaRuntimeException(ExceptionType type) {
        super(type.getMessage());
    }
}
