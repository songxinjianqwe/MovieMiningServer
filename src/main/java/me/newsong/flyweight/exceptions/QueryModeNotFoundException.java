package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.annotation.RESTField;
import me.newsong.flyweight.exceptions.annotation.RESTResponseStatus;
import me.newsong.flyweight.exceptions.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/3/20.
 */
@RESTResponseStatus(value = HttpStatus.NOT_FOUND, code = 5)
@RESTField("queryMode")
public class QueryModeNotFoundException extends BaseRESTException {


	public QueryModeNotFoundException(String mode) {
		super(mode);
	}
}
