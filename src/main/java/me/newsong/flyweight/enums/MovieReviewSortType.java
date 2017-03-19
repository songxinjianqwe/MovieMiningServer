package me.newsong.flyweight.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public enum MovieReviewSortType {
    Helpful, Time;
    private static final Map<String, MovieReviewSortType> stringToEnum = new HashMap<>();
    static {
        for (MovieReviewSortType type : values()) {
            stringToEnum.put(type.toString(), type);
        }
    }
    
    public static MovieReviewSortType fromString(String type) {
        if (!stringToEnum.containsKey(type)) {
            return null;
        }
        return stringToEnum.get(type);
    }
}
