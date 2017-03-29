package me.newsong.flyweight.service.iface;

import me.newsong.flyweight.domain.entity.Movie;
import me.newsong.flyweight.domain.entity.MovieReview;
import me.newsong.flyweight.domain.entity.PageBean;
import me.newsong.flyweight.domain.entity.RemoteMovieInfo;
import me.newsong.flyweight.domain.time.Day;
import me.newsong.flyweight.domain.time.Month;
import me.newsong.flyweight.enums.MovieReviewSortType;
import me.newsong.flyweight.enums.MovieSortType;
import me.newsong.flyweight.enums.MovieTag;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MovieService extends MovieReviewTemplate {
    Movie findMovieByID(String id);
    Movie findSimpleMovieByID(String id);

    //以下三个方法都是为获取电影信息服务的
    double getAverageScore(List<MovieReview> reviews);

    double getVarianceOfScore(List<MovieReview> reviews);

    Map<Integer, Long> findScoresByStar(String id);

    // 迭代二新需求
    List<String> findAllMovieNames();
    PageBean<RemoteMovieInfo> findMoviesByNames(String[] names, int currPage);
    
    PageBean<RemoteMovieInfo> findLatestMovies(int currPage);

    PageBean<RemoteMovieInfo> findMoviesByTag(MovieTag tag, MovieSortType sortBy, int currPage);

    List<MovieReview> findTop10MovieReviewsById(String id, MovieReviewSortType sort);
    
    Map<MovieTag, Double> findMovieTagProportions();

    Map<Long, Set<Double>> findReviewTimesAndScores();

    Map<Month, Double> findMovieScoresInMonthsById(String id);

    Map<Day, Double> findMovieScoresInDayByIdAndMonthSpan(String id, int monthSpan);
}
