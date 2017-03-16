package me.newsong.flyweight.service.iface;

import java.util.Date;
import java.util.List;
import java.util.Map;

import me.newsong.flyweight.domain.DescLengthRange;
import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.domain.User;

public interface UserService extends BaseMovieReviewTemplate {
    User findUserById(String id);

    Map<DescLengthRange, Long> findDescLengthsWithRange(String id, int gap);

    double getAverageDescLength(List<MovieReview> reviews);
}
