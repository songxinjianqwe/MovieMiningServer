package me.newsong.flyweight.controller;

import me.newsong.flyweight.domain.entity.Movie;
import me.newsong.flyweight.domain.entity.PageBean;
import me.newsong.flyweight.domain.entity.RemoteMovieInfo;
import me.newsong.flyweight.domain.time.BaseTimeUnit;
import me.newsong.flyweight.enums.MovieSortType;
import me.newsong.flyweight.enums.MovieTag;
import me.newsong.flyweight.enums.QueryMode;
import me.newsong.flyweight.enums.TimeUnit;
import me.newsong.flyweight.exceptions.QueryModeNotFoundException;
import me.newsong.flyweight.exceptions.SortTypeNotFoundException;
import me.newsong.flyweight.exceptions.TimeUnitNotFoundException;
import me.newsong.flyweight.service.iface.MovieService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * @author Sinjin Song
 */
@CrossOrigin
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieService service;

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ids", method = RequestMethod.GET)
    public List<String> findAllMovieIds() {
        return service.findAllIds();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/ids/{id}", method = RequestMethod.GET)
    public Movie findMovieByID(@PathVariable("id") String id) {
        return service.findMovieByID(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/scores_distribution", method = RequestMethod.GET)
    public Map<Integer, Long> findScoresByStar(@PathVariable("id") String id) {
        return service.findScoresByStar(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/{id}/accum_review_counts", method = RequestMethod.GET)
    public Map<? extends BaseTimeUnit, Long> findAccumulatedReviewCounts(@PathVariable("id") String id, @RequestParam("time_unit") String timeUnit,
                                                               @RequestParam("begin") Long begin, @RequestParam("end") Long end) {
        TimeUnit unit = TimeUnit.fromString(StringUtils.capitalize(timeUnit));
        if (unit == null) {
            throw new TimeUnitNotFoundException(timeUnit);
        }
        return service.findAccumulatedReviewCountsBy(unit, id, new Date(begin), new Date(end));
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/names", method = RequestMethod.GET)
    public List<String> findAllMovieNames() {
        return service.findAllMovieNames();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/names/{name}", method = RequestMethod.GET)
    public PageBean<RemoteMovieInfo> findMoviesByName(@PathVariable("name") String name, @RequestParam(value = "page", required = false, defaultValue = "0") int page,@RequestParam(value="mode",required =false,defaultValue = "single") String mode) {
        QueryMode queryMode = QueryMode.fromString(StringUtils.capitalize(mode));
        if(queryMode == QueryMode.Single){
            return service.findMoviesByName(name, page);
        }else if(queryMode == QueryMode.Batch){
            return service.findMoviesByNames(name.split(","),page);
        }else{
            throw new QueryModeNotFoundException(mode);
        }
    }


    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/latest", method = RequestMethod.GET)
    public PageBean<RemoteMovieInfo> findLatestMovies(@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        return service.findLatestMovies(page);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public MovieTag[] findAllMovieTags() {
        return MovieTag.values();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tags/{tag}", method = RequestMethod.GET)
    public PageBean<RemoteMovieInfo> findMoviesByTag(@PathVariable("tag") MovieTag tag, @RequestParam("sort") String sortBy, @RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        MovieSortType sort = MovieSortType.fromString(StringUtils.capitalize(sortBy));
        if (sort == null) {
            throw new SortTypeNotFoundException(sortBy);
        }
        return service.findMoviesByTag(tag, sort, page);
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/tags/proportions", method = RequestMethod.GET)
    public Map<MovieTag, Double> findMovieTagProportions() {
        return service.findMovieTagProportions();
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/review_times_and_scores", method = RequestMethod.GET)
    public Map<Long, Double> findReviewTimesAndScores() {
        return service.findReviewTimesAndScores();
    }

    @ResponseStatus(HttpStatus.OK)
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
