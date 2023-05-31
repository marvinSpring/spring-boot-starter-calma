package com.marvin.common.exception;

public class UnsupportedException extends CalmaRuntimeException implements Serializable{

    public UnsupportedException() {
    }

    public UnsupportedException(String message) {
        super(message);
    }

}
