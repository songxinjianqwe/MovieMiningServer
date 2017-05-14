package me.newsong.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public enum MovieSortType {
    Time, Score;
    private static final Map<String, MovieSortType> stringToEnum = new HashMap<>();

    static {
        for (MovieSortType type : values()) {
            stringToEnum.put(type.toString(), type);
        }
    }

    public static MovieSortType fromString(String type) {
        if(!stringToEnum.containsKey(type)){
            return null;
        }
        return stringToEnum.get(type);
    }

}
