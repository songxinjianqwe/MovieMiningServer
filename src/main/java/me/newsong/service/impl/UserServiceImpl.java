package me.newsong.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import me.newsong.dao.UserDOMapper;
import me.newsong.domain.common.DescLengthRange;
import me.newsong.domain.common.MovieReviewVO;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.entity.User;
import me.newsong.domain.entity.UserDO;
import me.newsong.exception.UserNotFoundException;
import me.newsong.service.UserService;
import me.newsong.util.MovieReviewConverter;
import me.newsong.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl extends MovieReviewTemplateImpl implements UserService {
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private MovieReviewConverter movieReviewConverter;
    
    @Override
    public List<String> findAllIds() {
        return userDOMapper.findAllUserIds();
    }

    @Override
    public User findUserById(String id) {
        List<MovieReviewDO> reviews = findMovieReviewDOsSortedByTimeDesc(id);
        return new User(id, reviews.get(0).getTime(), reviews.get(reviews.size() - 1).getTime(), reviews.size(),
                super.getKeyWords(reviews), getAverageDescLength(reviews));
    }

    @Override
    public List<String> findAllUserNames() {
        return userDOMapper.findAllNames();
    }

    @Override
    public PageInfo<UserDO> findUsersByContainingName(String name, int pageNum, int pageSize) {
        return addUserStatistics(userDOMapper.findByNameContaining(name, pageNum, pageSize).toPageInfo());
    }

    private PageInfo<UserDO> addUserStatistics(PageInfo<UserDO> infos) {
        for (UserDO info :
                infos.getList()) {
            info.setUser(findUserById(info.getUserId()));
        }
        return infos;
    }

    protected List<MovieReviewDO> findMovieReviewDOsById(String id) {
        List<MovieReviewDO> reviews = movieReviewDOMapper.findByUserId(id);
        if (reviews.size() == 0) {
            throw new UserNotFoundException(id);
        }
        return reviews;
    }

    @Override
    public Map<DescLengthRange, Long> findDescLengthsWithRange(String id, int gap) {
        return findMovieReviewDOsById(id).stream().map((review) -> review.getContent().length())
                .collect(Collectors.groupingBy((len) -> {
                    int level = len / gap;
                    DescLengthRange descLengthRange = new DescLengthRange(level * gap, (level + 1) * gap);
                    return descLengthRange;
                }, Collectors.counting()));
    }

    @Override
    public double getAverageDescLength(List<MovieReviewDO> reviews) {
        return reviews.stream().collect(Collectors.averagingDouble((review) -> review.getContent().length()));
    }

    @Override
    public UserDO findUserByUsername(String username) {
        return userDOMapper.findByUsername(username);
    }

    @Override
    public PageInfo<MovieReviewVO> findPagingMovieReviewsByUserRecommendId(Long userRecommendId, int pageNum, int pageSize) {
        Page<MovieReviewDO> page = movieReviewDOMapper.findByUserRecommendId(userRecommendId, pageNum, pageSize);
        return PageUtil.convertPage(page.toPageInfo(),movieReviewConverter);
    }
}
