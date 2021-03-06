package me.newsong.controller;

import com.github.pagehelper.PageInfo;
import me.newsong.domain.common.MovieVO;
import me.newsong.domain.common.PredictedMovieVO;
import me.newsong.domain.common.ReviewTimesAndScores;
import me.newsong.domain.entity.Movie;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.domain.time.BaseTimeUnit;
import me.newsong.enums.MovieSortType;
import me.newsong.enums.TimeUnit;
import me.newsong.exception.SortTypeNotFoundException;
import me.newsong.exception.TimeUnitNotFoundException;
import me.newsong.service.MovieService;
import me.newsong.util.Const;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @RequestMapping(value = "/ids", method = GET)
    public List<String> findAllMovieIds() {
        return service.findAllIds();
    }

    @RequestMapping(value = "/ids/{id}", method = GET)
    public Movie findMovieByID(@PathVariable("id") String id) {
        return service.findMovieByID(id);
    }

    @RequestMapping(value = "/{id}/scores_distribution", method = GET)
    public Map<Integer, Long> findScoresByStar(@PathVariable("id") String id) {
        return service.findScoresByStar(id);
    }

    @RequestMapping(value = "/{id}/accum_review_counts", method = GET)
    public Map<? extends BaseTimeUnit, Long> findAccumulatedReviewCounts(@PathVariable("id") String id, @RequestParam("time_unit") String timeUnit,
                                                                         @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN) @RequestParam("begin") LocalDateTime begin, @DateTimeFormat(pattern = Const.DATE_TIME_PATTERN) @RequestParam("end") LocalDateTime end) {
        TimeUnit unit = TimeUnit.fromString(StringUtils.capitalize(timeUnit));
        if (unit == null) {
            throw new TimeUnitNotFoundException(timeUnit);
        }
        return service.findAccumulatedReviewCountsBy(unit, id, begin, end);
    }

    @RequestMapping(value = "/names", method = GET)
    public List<String> findAllMovieNames() {
        return service.findAllMovieNames();
    }

    @RequestMapping(value = "/names/{name}", method = GET)
    public PageInfo<RemoteMovieInfoDO> findMoviesByNames(@PathVariable("name") String name, @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) throws UnsupportedEncodingException {
        return service.findMoviesByContainingName(URLDecoder.decode(name, "UTF-8").replace('*', '.'), pageNum, pageSize);
    }


    @RequestMapping(value = "/latest", method = GET)
    public PageInfo<RemoteMovieInfoDO> findLatestMovies(@RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        return service.findLatestMovies(pageNum, pageSize);
    }

    @RequestMapping(value = "/tags", method = GET)
    public List<String> findAllMovieTags() {
        return service.findAllMovieTags();
    }

    @RequestMapping(value = "/tags/{tag}", method = GET)
    public PageInfo<RemoteMovieInfoDO> findMoviesByTag(@PathVariable("tag") String tag, @RequestParam("sort") String sortBy, @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        MovieSortType sort = MovieSortType.fromString(StringUtils.capitalize(sortBy));
        if (sort == null) {
            throw new SortTypeNotFoundException(sortBy);
        }
        return service.findMoviesByTag(tag, sort, pageNum, pageSize);
    }

    @RequestMapping(value = "/tags/proportions", method = GET)
    public Map<String, Double> findMovieTagProportions() {
        return service.findMovieTagProportions();
    }

    @RequestMapping(value = "/review_times_and_scores", method = GET)
    public ReviewTimesAndScores findReviewTimesAndScores() {
        Map<Integer, Set<Double>> reviewTimesAndScores = service.findReviewTimesAndScores();
        double correlationCoefficient = service.getCorrelationCoefficientOfReviewTimesAndScores(reviewTimesAndScores);
        return new ReviewTimesAndScores(reviewTimesAndScores, correlationCoefficient);
    }


    @RequestMapping(value = "/{id}/scores_variation", method = GET)
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

    @RequestMapping(value = "/country/{country}")
    public PageInfo<RemoteMovieInfoDO> findMoviesByCountry(@PathVariable("country") String country, @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        return service.findByCountryContaining(country, pageNum, pageSize);
    }

    @RequestMapping(value = "/director/{director}")
    public PageInfo<RemoteMovieInfoDO> findMoviesByDirector(@PathVariable("director") String director, @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        return service.findByDirectorContaining(director, pageNum, pageSize);
    }


    @RequestMapping(value = "/language/{language}")
    public PageInfo<RemoteMovieInfoDO> findMoviesByLanguage(@PathVariable("language") String language, @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        return service.findByLanguageContaining(language, pageNum, pageSize);
    }

    @RequestMapping(value = "/writer/{writer}")
    public PageInfo<RemoteMovieInfoDO> findMoviesByWriter(@PathVariable("writer") String writer, @RequestParam(value = "pageNum", required = false, defaultValue = "0") Integer pageNum, @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize) {
        return service.findByWriterContaining(writer, pageNum, pageSize);
    }

    @RequestMapping(method = GET)
    public List<MovieVO> findAll() {
        return service.findDisplayMovies();
    }

    @RequestMapping(value = "/in_theaters", method = GET)
    public List<RemoteMovieInfoDO> findInTheaterMovies() {
        return service.findInTheatersMovies();
    }
    
    @RequestMapping(value = "/{id}/prediction", method = GET)
    public PredictedMovieVO predict(@PathVariable("id") String movieId) {
        return service.predict(movieId);
    }
    
    @RequestMapping(value = "/{index}/prediction_with_history",method = GET)
    public Map<Integer,Long> predictWithHistory(@PathVariable("index") Integer index){
        return service.predictWithHistory(index);
    }
}
