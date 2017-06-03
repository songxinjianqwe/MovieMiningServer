package me.newsong.service;

import me.newsong.domain.entity.RemoteMovieInfoDO;

import java.util.List;

/**
 * Created by SinjinSong on 2017/6/1.
 */
public interface RecommendService {
    List<RemoteMovieInfoDO> recommendForUser(Long userRecommendId);
    List<RemoteMovieInfoDO> findSimilarMovies(String movieId);
}
