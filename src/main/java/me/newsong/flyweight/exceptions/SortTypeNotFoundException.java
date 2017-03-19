package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class SortTypeNotFoundException extends BaseRestException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	private static final int CODE = 40403;
	public SortTypeNotFoundException(String sort) {
		super(STATUS, CODE, null, "sort",sort);
	}
}
