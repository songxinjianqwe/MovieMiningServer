package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.annotation.RESTField;
import me.newsong.flyweight.exceptions.annotation.RESTResponseStatus;
import org.springframework.http.HttpStatus;

import me.newsong.flyweight.exceptions.base.BaseRESTException;
@RESTResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, code = 1)
@RESTField("PythonServer")
public class PythonServerErrorException extends BaseRESTException {


	public PythonServerErrorException() {
		super("Error");
	}
}
