package me.newsong.flyweight.enums;

import java.util.HashMap;
import java.util.Map;

public enum TimeUnit {
	Month,Season,Day;
	 private static final Map<String, TimeUnit> stringToEnum = new HashMap<>();
    
    static {
        for (TimeUnit type : values()) {
            stringToEnum.put(type.toString(), type);
        }
    }

    public static TimeUnit fromString(String type) {
        if(!stringToEnum.containsKey(type)){
            return null;
        }
        return stringToEnum.get(type);
    }
}
