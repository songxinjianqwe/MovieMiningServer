package me.newsong.exception;

import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@RESTResponseStatus(value = HttpStatus.NOT_FOUND, code = 4)
@RESTField("timeUnit")
public class TimeUnitNotFoundException extends BaseRESTException {
	public TimeUnitNotFoundException(String unit) {
		super(unit);
	}
}
