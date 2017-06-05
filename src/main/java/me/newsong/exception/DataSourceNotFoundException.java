package me.newsong.exception;

import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/6/5.
 */
@RESTResponseStatus(value= HttpStatus.NOT_FOUND, code=5)
@RESTField("url")
public class DataSourceNotFoundException extends BaseRESTException{
    public DataSourceNotFoundException(String url){
        super(url);
    }
}
