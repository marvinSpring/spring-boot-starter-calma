package com.marvin.common.exception;

import com.marvin.config.anno.WebIGExceptionListener;
import org.springframework.lang.Nullable;

/**
 * 标记不需要通知的异常，该异常被抛出的时候，calma将会忽略不纳入通知范围
 * 而只将其计入统计（以方便在您需要的时候将其情况叙述出来），
 * 用于项目中您觉得不重要的异常的情况.
 *
 * 例如：
 * <pre>{@code
 * public void bizMethod() throws IGException{
 *     try {
 *       //你可能出问题的业务代码并且该问题你不想去通知
 *       ...;
 *     } catch(BizException e) {
 *         throw new IGException(e.getMessage());
 *     }
 * }
 * }
 * </pre>
 *
 * @see WebIGExceptionListener
 * @author Marvin
 */
public class IGException extends RuntimeException {

    public IGException(String msg) {
        super(msg);
    }

    public IGException(@Nullable String msg, @Nullable Throwable cause) {
        super(msg, cause);
    }

}

