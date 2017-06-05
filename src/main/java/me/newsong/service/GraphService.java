package me.newsong.service;

import java.util.Map;

/**
 * Created by SinjinSong on 2017/6/1.
 */
public interface GraphService {
    String getHistDistributionOfImdbScore(Integer bins, Boolean normed);

    String getHistDistributionOfImdbReviewCount(Integer bins, Boolean normed);

    Map<Double, Double> getCdfOfImdbScore();

    Map<Double, Double> getKdeOfImdbScore();

    Map<Double, Double> getKdeOfImdbReviewCount();

    String getScoreOfSingleYear();

    String getBoxOfficeOfSingleYear();
}
