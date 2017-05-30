package me.newsong.exception;


import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/7.
 */
@RESTResponseStatus(value= HttpStatus.UNAUTHORIZED,code=4)
@RESTField("tokenStatus")
public class TokenStateInvalidException extends BaseRESTException {
    public TokenStateInvalidException(String tokenStatus){
        super(tokenStatus);
    }
}
