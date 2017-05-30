package me.newsong.exception;


import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/5/9.
 */
@RESTResponseStatus(value = HttpStatus.FORBIDDEN,code=1)
@RESTField("role")
public class AccessDeniedException extends BaseRESTException {
    public AccessDeniedException(String role){
        super(role);
    }
}
