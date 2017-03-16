package me.newsong.flyweight.dao.iface.movie_review;

import java.util.List;

import me.newsong.flyweight.domain.MovieReview;

public interface MovieReviewRepository {
    List<MovieReview> findByMovieId(String id);

    List<MovieReview> findByUserId(String id);

    List<String> findAllMovieIds();

    List<String> findAllUserIds();
}
