package me.newsong.flyweight.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/20.
 */
public enum QueryMode {
    Single,Batch;
    private static final Map<String, QueryMode> stringToEnum = new HashMap<>();

    static {
        for (QueryMode type : values()) {
            stringToEnum.put(type.toString(), type);
        }
    }

    public static QueryMode fromString(String type) {
        if(!stringToEnum.containsKey(type)){
            return null;
        }
        return stringToEnum.get(type);
    }
}
