package me.newsong.service;

import com.github.pagehelper.PageInfo;
import me.newsong.domain.common.DescLengthRange;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.entity.User;
import me.newsong.domain.entity.UserDO;

import java.util.List;
import java.util.Map;

public interface UserService extends MovieReviewTemplate {
    User findUserById(String id);
    List<String> findAllUserNames();
    PageInfo<UserDO> findUsersByContainingName(String name, int pageNum, int pageSize);
    Map<DescLengthRange, Long> findDescLengthsWithRange(String id, int gap);
    double getAverageDescLength(List<MovieReviewDO> reviews);
}
