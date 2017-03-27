package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.annotation.RESTField;
import me.newsong.flyweight.exceptions.annotation.RESTResponseStatus;
import me.newsong.flyweight.exceptions.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@RESTResponseStatus(value = HttpStatus.BAD_REQUEST, code = 1)
@RESTField("totalPage")
public class PageOutOfBoundsException extends BaseRESTException {
    public PageOutOfBoundsException(int totalPage) {
        super(totalPage);
    }
}
