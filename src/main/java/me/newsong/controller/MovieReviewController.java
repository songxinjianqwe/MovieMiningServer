package me.newsong.controller;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.newsong.domain.common.MovieReviewDTO;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.enums.MovieReviewSortType;
import me.newsong.exception.SortTypeNotFoundException;
import me.newsong.security.domain.JWTUser;
import me.newsong.service.MovieService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@RestController
@CrossOrigin
@RequestMapping("/movie_reviews")
@Slf4j
public class MovieReviewController {
    @Autowired
    private MovieService service;
    
    @RequestMapping("/{id}")
    public PageInfo<MovieReviewDO> findMovieReviewsByMovieId(@PathVariable("id") String id, @RequestParam("sort") String sortBy, @RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        MovieReviewSortType sort = MovieReviewSortType.fromString(StringUtils.capitalize(sortBy));
        if (sort == null) {
            throw new SortTypeNotFoundException(sortBy);
        }
        return service.findSortedMovieReviewsById(id, sort,pageNum,pageSize);
    }
    
    @RequestMapping(method=RequestMethod.POST)
    public void review(@Valid @RequestBody MovieReviewDTO movieReview, @AuthenticationPrincipal  JWTUser user){
        log.info("{}",movieReview);  
        MovieReviewDO movieReviewDO = new MovieReviewDO();
        movieReviewDO.setMovieId(movieReview.getMovieId());
        movieReviewDO.setTime(LocalDateTime.now());
        movieReviewDO.setViewTimes(0);
        movieReviewDO.setHelpfulTimes(0);
        movieReviewDO.setScore(movieReview.getScore());
        movieReviewDO.setDisplay(Boolean.TRUE);
        movieReviewDO.setSummary(movieReview.getSummary());
        movieReviewDO.setContent(movieReview.getContent());
        movieReviewDO.setUserId(user.getUser().getUserId() != null ? user.getUser().getUserId() : user.getUsername());
        service.addMovieReview(movieReviewDO);
    }
}
