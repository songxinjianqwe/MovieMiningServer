package me.newsong.exception;

import me.newsong.exception.annotation.RESTField;
import me.newsong.exception.annotation.RESTResponseStatus;
import me.newsong.exception.base.BaseRESTException;
import org.springframework.http.HttpStatus;

@RESTResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, code = 1)
@RESTField("PythonServer")
public class PythonServerErrorException extends BaseRESTException {


	public PythonServerErrorException() {
		super("Error");
	}
}
