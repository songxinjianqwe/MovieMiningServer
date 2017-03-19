package me.newsong.flyweight.service.iface;

import java.util.List;
import java.util.Map;

import me.newsong.flyweight.domain.DescLengthRange;
import me.newsong.flyweight.domain.entity.MovieReview;
import me.newsong.flyweight.domain.entity.User;

public interface UserService extends MovieReviewTemplate {
    User findUserById(String id);

    Map<DescLengthRange, Long> findDescLengthsWithRange(String id, int gap);

    double getAverageDescLength(List<MovieReview> reviews);
}
