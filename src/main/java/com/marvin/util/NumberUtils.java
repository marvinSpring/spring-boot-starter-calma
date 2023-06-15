package com.marvin.util;

import com.marvin.common.constant.ExceptionConstant;
import com.marvin.common.exception.internal.TypeConvertException;
import org.springframework.util.StringUtils;

public class NumberUtils {

    public static long strToLong(String number, String varName) {
        if (StringUtils.isEmpty(varName)) {
            throw new RuntimeException(ExceptionConstant.Internal.internalException("101718"));
        }
        if (StringUtils.isEmpty(number)) {
            throw new NullPointerException(String.format(ExceptionConstant.VARIABLE_IS_NULL, varName));
        }
        Long value = null;
        try {
            value = Long.valueOf(number);
        } catch (NumberFormatException e) {
            throw new TypeConvertException(ExceptionConstant.typeConvertErrorText(String.class.getName(), Long.class.getName(),varName));
        }
        return value;
    }

}
