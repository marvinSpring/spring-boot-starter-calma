package com.marvin.util;

import java.util.Collection;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamUtils {

    public static <T,R> Collection<R> toList(Collection<T> collection, Function<T,R> function){
        return collection.stream().map(function).collect(Collectors.toList());
    }
}
