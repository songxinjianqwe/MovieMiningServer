package me.newsong.controller;

import com.github.pagehelper.PageInfo;
import me.newsong.domain.common.DescLengthRange;
import me.newsong.domain.common.MovieReviewVO;
import me.newsong.domain.entity.User;
import me.newsong.domain.entity.UserDO;
import me.newsong.domain.time.BaseTimeUnit;
import me.newsong.enums.TimeUnit;
import me.newsong.exception.TimeUnitNotFoundException;
import me.newsong.security.domain.JWTUser;
import me.newsong.service.UserService;
import me.newsong.util.Const;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService service;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public List<String> findAllUserIds() {
        return service.findAllIds();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public List<String> findAllUserNames() {
        return service.findAllUserNames();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ids/{id}", method = RequestMethod.GET)
    public User findMovieByID(@PathVariable("id") String id) {
        return service.findUserById(id);
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
    public PageInfo<UserDO> findMovieByNameContaining(@PathVariable("name") String name, @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        return service.findUsersByContainingName(name,pageNum,pageSize);
    }
    

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/desc_lengths", method = RequestMethod.GET)
    public Map<DescLengthRange, Long> findDescLengthsWithRange(@PathVariable("id") String id,
                                                                @RequestParam(defaultValue = "20", required = false) int gap) {
        
        return service.findDescLengthsWithRange(id, gap);
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/accum_review_counts", method = RequestMethod.GET)
    public Map<? extends BaseTimeUnit, Long> findAccumulatedReviewCounts(@PathVariable("id") String id, @RequestParam("time_unit") String timeUnit,
                                                                         @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN) @RequestParam("begin") LocalDateTime begin, @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN)  @RequestParam("end") LocalDateTime end) {
        TimeUnit unit = TimeUnit.fromString(StringUtils.capitalize(timeUnit));
        if (unit == null) {
            throw new TimeUnitNotFoundException(timeUnit);
        }
        return service.findAccumulatedReviewCountsBy(unit, id, begin, end);
    }
    
    @RequestMapping(value="/movie_reviews",method = RequestMethod.GET)
    public PageInfo<MovieReviewVO> findMovieReviews(@AuthenticationPrincipal JWTUser user, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize){
        return  service.findPagingMovieReviewsByUserRecommendId(user.getUser().getUserRecommendId(), pageNum, pageSize);
    }
}
