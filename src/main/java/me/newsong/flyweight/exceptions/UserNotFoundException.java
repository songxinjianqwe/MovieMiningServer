package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.annotation.RESTField;
import me.newsong.flyweight.exceptions.annotation.RESTResponseStatus;
import org.springframework.http.HttpStatus;

import me.newsong.flyweight.exceptions.base.BaseRESTException;

@RESTResponseStatus(value = HttpStatus.NOT_FOUND, code = 1)
@RESTField("id")
public class UserNotFoundException extends BaseRESTException {
    public UserNotFoundException(String id) {
        super(id);
    }

}
