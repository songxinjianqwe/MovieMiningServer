package me.newsong.exception.annotation;

import org.springframework.http.HttpStatus;

import java.lang.annotation.*;

/**
 * Created by SinjinSong on 2017/3/27.
 * 
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface RESTResponseStatus {
    /**
     * HTTP状态码
     * @return
     */
    HttpStatus value() ;

    /**
     * 错误码，同一种HTTP状态码的错误码从1开始递增
     * @return
     */
    int code();

    /**
     * 对于单一属性错误，其在国际化资源文件中的key
     * 默认值是异常类名的除了Exception其他部分
     * 多个属性错误的不需指定
     * @return
     */
    String msgKey() default "";

    /**
     * 关于此错误的更多信息URL
     * @return
     */
    String url() default  "";
}
