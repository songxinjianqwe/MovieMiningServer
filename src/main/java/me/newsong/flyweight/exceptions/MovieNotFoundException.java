package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.annotation.RESTField;
import me.newsong.flyweight.exceptions.annotation.RESTResponseStatus;
import org.springframework.http.HttpStatus;

import me.newsong.flyweight.exceptions.base.BaseRESTException;

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
