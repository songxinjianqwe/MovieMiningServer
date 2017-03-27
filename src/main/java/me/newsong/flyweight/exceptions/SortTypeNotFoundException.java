package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.annotation.RESTField;
import me.newsong.flyweight.exceptions.annotation.RESTResponseStatus;
import me.newsong.flyweight.exceptions.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@RESTResponseStatus(value = HttpStatus.NOT_FOUND, code = 3)
@RESTField("sort")
public class SortTypeNotFoundException extends BaseRESTException {
    public SortTypeNotFoundException(String sort) {
        super(sort);
    }
}
