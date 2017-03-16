package me.newsong.flyweight.service.iface;

import me.newsong.flyweight.domain.MovieReview;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/16.
 */
public interface BaseMovieReviewTemplate {
    List<String> findAllIds();
    <T> Map<T, Long> findAccumulatedReviewCountsBy(Class<T> unit, String id, Date begin, Date end);
    List<String> getKeyWords(List<MovieReview> reviews);
}
