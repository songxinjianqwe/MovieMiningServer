package me.newsong.flyweight.controller;

import me.newsong.flyweight.domain.entity.MovieReview;
import me.newsong.flyweight.enums.MovieReviewSortType;
import me.newsong.flyweight.enums.MovieSortType;
import me.newsong.flyweight.exceptions.SortTypeNotFoundException;
import me.newsong.flyweight.service.iface.MovieService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<MovieReview> findMovieReviewsByMovieId(@PathVariable("id") String id, @RequestParam("sort") String sortBy) {
        MovieReviewSortType sort = MovieReviewSortType.fromString(StringUtils.capitalize(sortBy));
        if (sort == null) {
            throw new SortTypeNotFoundException(sortBy);
        }
        return service.findTop10MovieReviewsById(id, sort);
    }
}
