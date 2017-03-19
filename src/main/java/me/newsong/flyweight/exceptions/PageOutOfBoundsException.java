package me.newsong.flyweight.exceptions;

import me.newsong.flyweight.exceptions.base.BaseRestException;
import org.springframework.http.HttpStatus;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class PageOutOfBoundsException extends BaseRestException {
    private static final HttpStatus STATUS = HttpStatus.BAD_REQUEST;
    private static final int CODE = 40001;
    public PageOutOfBoundsException(int totalPage) {
        super(STATUS, CODE, null, "totalPage", totalPage);
    }
}
