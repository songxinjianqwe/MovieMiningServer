package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.enums.TimeUnit;
import me.newsong.flyweight.exceptions.base.BaseRestException;
import org.springframework.http.HttpStatus;

import java.util.Locale;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class TimeUnitNotFoundException extends BaseRestException {
    private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	private static final int CODE = 40404;


	public TimeUnitNotFoundException(String unit) {
		super(STATUS, CODE, null, "TimeUnit",unit);
	}
}
