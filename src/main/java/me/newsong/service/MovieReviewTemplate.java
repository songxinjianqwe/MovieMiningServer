package me.newsong.service;


import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.enums.TimeUnit;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/16.
 */
public interface MovieReviewTemplate {
    List<String> findAllIds();
    <T> Map<T, Long> findAccumulatedReviewCountsBy(TimeUnit unit, String id, LocalDateTime begin, LocalDateTime end);
    List<String> getKeyWords(List<MovieReviewDO> reviews);
}
