package com.marvin.exception;

import com.marvin.enumeration.ExceptionType;

import java.io.Serializable;

//用于未捕捉到的异常
public class CalmaUnCatchException extends RuntimeException implements Serializable {

    private static final Long serializableId = 0L;

    public CalmaRuntimeException(ExceptionType type,Throwable throwable) {
        super(type.getMessage(),throwable);
    }
  
  public void exceptionMessage(){
  
  }

}
