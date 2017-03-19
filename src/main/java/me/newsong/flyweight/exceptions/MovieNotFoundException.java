package me.newsong.flyweight.exceptions;

import java.util.Locale;

import org.springframework.http.HttpStatus;

import me.newsong.flyweight.exceptions.base.BaseRestException;

public class MovieNotFoundException extends BaseRestException{
	private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	private static final int CODE = 40402;
	public MovieNotFoundException(String idOrName) {
		super(STATUS, CODE, null, "id/name",idOrName);
	}
	
}
