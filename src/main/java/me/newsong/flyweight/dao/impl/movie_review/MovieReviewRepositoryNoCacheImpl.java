package me.newsong.flyweight.dao.impl.movie_review;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.MovieReview;

import java.util.List;

/**
 * Created by SinjinSong on 2017/3/16.
 */
public class MovieReviewRepositoryNoCacheImpl implements MovieReviewRepository {
    
    @Override
    public List<MovieReview> findByMovieId(String id) {
        return null;
    }

    @Override
    public List<MovieReview> findByUserId(String id) {
        return null;
    }

    @Override
    public List<String> findAllMovieIds() {
        return null;
    }

    @Override
    public List<String> findAllUserIds() {
        return null;
    }
}
