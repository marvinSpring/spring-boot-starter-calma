package com.marvin.exception;

import com.marvin.enumeration.ExceptionType;

import java.io.Serializable;

public class NoSuchHttpRequestMethodException extends CalmaRuntimeException implements Serializable {

    public NoSuchHttpRequestMethodException(ExceptionType type, Throwable throwable) {
        super(type, throwable);
    }
}
