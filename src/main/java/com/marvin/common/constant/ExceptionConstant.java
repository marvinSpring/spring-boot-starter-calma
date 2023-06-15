package com.marvin.common.constant;

public class ExceptionConstant {

    private static final String TYPE_CANNOT_BE_CONVERT = "type {%s} cannot convert to {%s},please check your configuration\n The following information may be help you:\n {%s}";

    public static final String VARIABLE_IS_NULL = "variable {%s} cannot convert be null";

    public static String typeConvertErrorText(String old, String newest, String varName) {
        return String.format(TYPE_CANNOT_BE_CONVERT, old, newest, varName);
    }

    static public class Internal {

        private static final String EXCEPTION = "error code is {%s},Please contact me if you see this Exception.\n mail: {%s}";

        //框架全局联系人邮件
        private static final String MAIL = "1261626796@qq.com";

        public static String internalException(String code) {
            return String.format(EXCEPTION, code, MAIL);
        }

    }

}
