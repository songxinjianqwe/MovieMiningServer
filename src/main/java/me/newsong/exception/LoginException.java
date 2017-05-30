package me.newsong.exception;

import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/29.
 */
@RESTResponseStatus(value = HttpStatus.UNAUTHORIZED,code =1)
@RESTField("loginInfo")
public class LoginException extends BaseRESTException{
    public LoginException(){
        super("NotCompleted");
    }
}
