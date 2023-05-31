package com.marvin.config.anno;

import java.lang.annotation.*;

/**
 * 把它放在你不像被监听的controller上，
 * 他将像医学中的抗生素一般，将你不会去将该controller中产生的任何异常进行通知，
 * 但它会偷偷的在本地记录（为了方便后续你需要的时候使用）
 *
 * 例如：
 * <pre>{@code
 * @IGExceptionListener
 * @Controller
 * public class FooController{
 *
 *     @XxxMapping
 *     public Foo fooMethod(){
 *         return new Foo();
 *     }
 * }
 * }
 * </pre>
 *
 * @see com.marvin.common.exception.IGException
 * @author marvin
 */
@Documented
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WebIGExceptionListener {

}
