package com.marvin;

import java.io.Serializable;

//本stater的内部异常，用来处理内部问题，在别的项目引用本项目时，可能会有本项目报错的问题，这个时候它就不能很好的吧问题抛出来
public class PiracyException extends Exception implements Serializable {

    private static final Long serializableId = 0L;

    public PiracyException(String message) {
        super(message);
    }

}
