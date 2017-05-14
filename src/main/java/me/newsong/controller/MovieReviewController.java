package me.newsong.controller;

import com.github.pagehelper.PageInfo;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.enums.MovieReviewSortType;
import me.newsong.exception.SortTypeNotFoundException;
import me.newsong.service.MovieService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@RestController
@CrossOrigin
@RequestMapping("/movie_reviews")
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
}
