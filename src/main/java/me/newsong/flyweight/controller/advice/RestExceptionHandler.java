package me.newsong.flyweight.controller.advice;

import me.newsong.flyweight.exceptions.domain.RESTError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import me.newsong.flyweight.exceptions.base.BaseRESTException;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(BaseRESTException.class)
	public ResponseEntity<RESTError> handle(BaseRESTException e) {
		return new ResponseEntity<>(new RESTError(e.getStatus(), e.getCode(), e.getErrors(), ""), e.getStatus());
	}
	
}
