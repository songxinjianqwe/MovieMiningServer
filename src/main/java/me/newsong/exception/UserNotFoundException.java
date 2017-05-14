package me.newsong.exception;

import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

@RESTResponseStatus(value = HttpStatus.NOT_FOUND, code = 1)
@RESTField("id")
public class UserNotFoundException extends BaseRESTException {
    public UserNotFoundException(String id) {
        super(id);
    }

}
