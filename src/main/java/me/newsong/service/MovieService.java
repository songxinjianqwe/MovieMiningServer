package me.newsong.service;


import com.github.pagehelper.PageInfo;
import me.newsong.domain.common.MovieVO;
import me.newsong.domain.common.PredictedMovieVO;
import me.newsong.domain.entity.Movie;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.domain.time.Day;
import me.newsong.domain.time.Month;
import me.newsong.enums.MovieReviewSortType;
import me.newsong.enums.MovieSortType;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MovieService extends MovieReviewTemplate {
    Movie findMovieByID(String id);

    //以下三个方法都是为获取电影信息服务的
    double getAverageScore(List<MovieReviewDO> reviews);

    double getVarianceOfScore(List<MovieReviewDO> reviews);

    Map<Integer, Long> findScoresByStar(String id);
    
    // 迭代二新需求
    List<String> findAllMovieNames();

    PageInfo<RemoteMovieInfoDO> findMoviesByContainingName(String name, int pageNum, int pageSize);

    PageInfo<RemoteMovieInfoDO> findLatestMovies(int pageNum, int pageSize);

    PageInfo<RemoteMovieInfoDO> findMoviesByTag(String tag, MovieSortType sortBy, int pageNum, int pageSize);

    PageInfo<MovieReviewDO> findSortedMovieReviewsById(String id, MovieReviewSortType sort, int pageNum, int pageSize);

    Map<String, Double> findMovieTagProportions();

    Map<Integer, Set<Double>> findReviewTimesAndScores();

    double getCorrelationCoefficientOfReviewTimesAndScores(Map<Integer, Set<Double>> map);

    Map<Month, Double> findMovieScoresInMonthsById(String id);

    Map<Day, Double> findMovieScoresInDayByIdAndMonthSpan(String id, int monthSpan);

    List<String> findAllMovieTags();

    PageInfo<RemoteMovieInfoDO> findByMovieIdContaining(String movieId, int pageNum, int pageSize);

    PageInfo<RemoteMovieInfoDO> findByCountryContaining(String country, int pageNum, int pageSize);

    PageInfo<RemoteMovieInfoDO> findByDirectorContaining(String director, int pageNum, int pageSize);

    PageInfo<RemoteMovieInfoDO> findByLanguageContaining(String language, int pageNum, int pageSize);

    PageInfo<RemoteMovieInfoDO> findByWriterContaining(String writer, int pageNum, int pageSize);
 
    List<MovieVO> findDisplayMovies();
    
    void addMovieReview(MovieReviewDO movieReviewDO);
    
    List<RemoteMovieInfoDO> findInTheatersMovies();
    
    PredictedMovieVO predict(String movieId);
}
