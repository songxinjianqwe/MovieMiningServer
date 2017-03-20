package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/3/20.
 */
public class QueryModeNotFoundException extends BaseRestException {
     private static final HttpStatus STATUS = HttpStatus.NOT_FOUND;
	private static final int CODE = 40405;


	public QueryModeNotFoundException(String mode) {
		super(STATUS, CODE, null, "queryMode",mode);
	}
}
