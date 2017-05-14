package me.newsong.exception;

import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * 所有自定义异常类起码需要加一个注解，即
 */
@RESTResponseStatus(value = HttpStatus.NOT_FOUND, code = 2)
@RESTField("id/name")
public class MovieNotFoundException extends BaseRESTException {
    public MovieNotFoundException(String idOrName) {
        super(idOrName);
    }
}
