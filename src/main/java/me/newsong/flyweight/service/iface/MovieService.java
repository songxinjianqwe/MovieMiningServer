package me.newsong.flyweight.service.iface;

import java.util.List;
import java.util.Map;

import me.newsong.flyweight.domain.Movie;
import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.domain.RemoteMovieInfo;

public interface MovieService extends MovieReviewTemplate {
	Movie findMovieByID(String id);
	//以下三个方法都是为获取电影信息服务的
	double getAverageScore(List<MovieReview> reviews);
	double getVarianceOfScore(List<MovieReview> reviews);
	
	Map<Integer,Long> findScoresByStar(String id);
	List<RemoteMovieInfo> findMoviesByName(String names);
}
