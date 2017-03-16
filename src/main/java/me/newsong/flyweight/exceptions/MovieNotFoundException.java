package me.newsong.flyweight.exceptions;

import java.util.Locale;

import org.springframework.http.HttpStatus;

import me.newsong.flyweight.exceptions.base.BaseRestException;

public class MovieNotFoundException extends BaseRestException{
	private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	private static final int CODE = 40402;
	/**
	 * 
	 */
	private static final long serialVersionUID = -4228289840877921235L;
	public MovieNotFoundException(String id,Locale locale) {
		super(STATUS, CODE, locale, "id",id);
	}
	
}
