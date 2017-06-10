package me.newsong.exception;

import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/6/10.
 */
@RESTResponseStatus(value= HttpStatus.BAD_REQUEST,code=3)
@RESTField("index")
public class GraphInfoNotEnoughException extends BaseRESTException {
    public GraphInfoNotEnoughException(int index) {
        super(index);
    }
}
