package com.marvin.common.exception;


import com.marvin.common.enumeration.ExceptionType;

import java.io.Serializable;

public class NoSuchHttpRequestMethodException extends CalmaRuntimeException implements Serializable {

    public NoSuchHttpRequestMethodException(ExceptionType type) {
        super(type);
    }

    public NoSuchHttpRequestMethodException(ExceptionType type, Throwable throwable) {
        super(type, throwable);
    }
}
