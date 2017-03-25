package me.newsong.flyweight.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class MapUtil {
    public static <K,V> void putMultiValue(Map<K,List<V>> map, K k, V v){
        if(map.get(k) == null){
            List<V> values = new ArrayList<V>();
            values.add(v);
            map.put(k,values);
        }else{
            map.get(k).add(v);
        }
    }
}