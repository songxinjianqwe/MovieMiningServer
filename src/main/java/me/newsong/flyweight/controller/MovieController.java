package me.newsong.flyweight.controller;

import me.newsong.flyweight.domain.entity.Movie;
import me.newsong.flyweight.domain.entity.PageBean;
import me.newsong.flyweight.domain.entity.RemoteMovieInfo;
import me.newsong.flyweight.domain.entity.ReviewTimesAndScores;
import me.newsong.flyweight.domain.time.BaseTimeUnit;
import me.newsong.flyweight.enums.MovieSortType;
import me.newsong.flyweight.enums.MovieTag;
import me.newsong.flyweight.enums.TimeUnit;
import me.newsong.flyweight.exceptions.PageOutOfBoundsException;
import me.newsong.flyweight.exceptions.SortTypeNotFoundException;
import me.newsong.flyweight.exceptions.TimeUnitNotFoundException;
import me.newsong.flyweight.service.iface.MovieService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * @author Sinjin Song
 *         方法不需要加Locale参数
 *         可以使用
 *         LocaleContextHolder.getLocale()获得Locale
 *         无论是逻辑层还是控制器都可通过此方法获得
 */
@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public List<String> findAllMovieIds() {
        return service.findAllIds();
    }

    @RequestMapping(value = "/ids/{id}", method = RequestMethod.GET)
    public Movie findMovieByID(@PathVariable("id") String id) {
        return service.findMovieByID(id);
    }

    @RequestMapping(value = "/{id}/scores_distribution", method = RequestMethod.GET)
    public Map<Integer, Long> findScoresByStar(@PathVariable("id") String id) {
        return service.findScoresByStar(id);
    }

    @RequestMapping(value = "/{id}/accum_review_counts", method = RequestMethod.GET)
    public Map<? extends BaseTimeUnit, Long> findAccumulatedReviewCounts(@PathVariable("id") String id, @RequestParam("time_unit") String timeUnit,
                                                                         @RequestParam("begin") Long begin, @RequestParam("end") Long end) {
        TimeUnit unit = TimeUnit.fromString(StringUtils.capitalize(timeUnit));
        if (unit == null) {
            throw new TimeUnitNotFoundException(timeUnit);
        }
        return service.findAccumulatedReviewCountsBy(unit, id, new Date(begin), new Date(end));
    }

    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public List<String> findAllMovieNames() {
        return service.findAllMovieNames();
    }

    @RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
    public PageBean<RemoteMovieInfo> findMoviesByNames(@PathVariable("name") String name, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        if (page < 0) {
            throw new PageOutOfBoundsException(page);
        }
        String[] names = name.split("_");
        System.out.println(Arrays.toString(names));
        try {
            for (int i = 0; i < names.length; ++i) {
                names[i] = URLDecoder.decode(names[i], "UTF-8").replace('*', '.');
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(Arrays.toString(names));
        return service.findMoviesByNames(names, page);
    }


    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public PageBean<RemoteMovieInfo> findLatestMovies(@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        if (page < 0) {
            throw new PageOutOfBoundsException(page);
        }
        return service.findLatestMovies(page);
    }

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public MovieTag[] findAllMovieTags() {
        return MovieTag.values();
    }

    @RequestMapping(value = "/tags/{tag}", method = RequestMethod.GET)
    public PageBean<RemoteMovieInfo> findMoviesByTag(@PathVariable("tag") MovieTag tag, @RequestParam("sort") String sortBy, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        MovieSortType sort = MovieSortType.fromString(StringUtils.capitalize(sortBy));
        if (sort == null) {
            throw new SortTypeNotFoundException(sortBy);
        }
        return service.findMoviesByTag(tag, sort, page);
    }

    @RequestMapping(value = "/tags/proportions", method = RequestMethod.GET)
    public Map<MovieTag, Double> findMovieTagProportions() {
        return service.findMovieTagProportions();
    }

    @RequestMapping(value = "/review_times_and_scores", method = RequestMethod.GET)
    public ReviewTimesAndScores findReviewTimesAndScores() {
        Map<Integer, Set<Double>> reviewTimesAndScores = service.findReviewTimesAndScores();
        double correlationCoefficient = service.getCorrelationCoefficientOfReviewTimesAndScores(reviewTimesAndScores);
        return new ReviewTimesAndScores(reviewTimesAndScores,correlationCoefficient);
    }


    @RequestMapping(value = "/{id}/scores_variation", method = RequestMethod.GET)
    public Map<? extends BaseTimeUnit, Double> findMovieScores(@PathVariable("id") String id, @RequestParam("time_unit") String timeUnit, @RequestParam(value = "month_span", required = false, defaultValue = "0") int monthSpan) {
        TimeUnit unit = TimeUnit.fromString(StringUtils.capitalize(timeUnit));
        if (unit == TimeUnit.Month) {
            return service.findMovieScoresInMonthsById(id);
        } else if (unit == TimeUnit.Day) {
            return service.findMovieScoresInDayByIdAndMonthSpan(id, monthSpan);
        } else {
            throw new TimeUnitNotFoundException(timeUnit);
        }
    }
}
