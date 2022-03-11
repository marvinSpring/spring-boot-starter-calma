package com.marvin.enumeration;

public enum ExceptionType {

    ERROR_OBTAINING_METHOD(9521,"Request method not found or empty."),;

    private final Integer value;
    private final String message;

    public Integer getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    ExceptionType(Integer value, String message) {
        this.value = value;
        this.message = message;
    }
}
