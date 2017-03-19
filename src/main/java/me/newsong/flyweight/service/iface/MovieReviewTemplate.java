package me.newsong.flyweight.service.iface;

import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.enums.TimeUnit;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/16.
 */
public interface MovieReviewTemplate {
    List<String> findAllIds();
    <T> Map<T, Long> findAccumulatedReviewCountsBy(TimeUnit unit, String id, Date begin, Date end);
    List<String> getKeyWords(List<MovieReview> reviews);
}
