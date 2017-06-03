package me.newsong.service;

/**
 * Created by SinjinSong on 2017/6/1.
 */
public interface GraphService {
    String getHistDistributionOfImdbScore(Integer bins, Boolean normed);
    String getHistDistributionOfImdbReviewCount(Integer bins, Boolean normed);
    String getCdfOfImdbScore();
    String getKdeOfImdbScore();
    String getKdeOfImdbReviewCount();
    String getScoreOfSingleYear();
    String getBoxOfficeOfSingleYear();
}
