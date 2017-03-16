package me.newsong.flyweight.exceptions;

import org.springframework.http.HttpStatus;

import me.newsong.flyweight.exceptions.base.BaseRestException;

public class PythonServerErrorException extends BaseRestException{
	private static final HttpStatus STATUS = HttpStatus.SERVICE_UNAVAILABLE;
	private static final int CODE = 50301;

	/**
	 * 
	 */
	private static final long serialVersionUID = 2471700178460343776L;


	public PythonServerErrorException() {
		super(STATUS, CODE, null, "PythonServer","Error");
	}
}
