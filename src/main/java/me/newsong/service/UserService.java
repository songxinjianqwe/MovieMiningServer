package me.newsong.service;

import me.newsong.domain.common.DescLengthRange;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService extends MovieReviewTemplate {
    User findUserById(String id);

    Map<DescLengthRange, Long> findDescLengthsWithRange(String id, int gap);

    double getAverageDescLength(List<MovieReviewDO> reviews);
}
