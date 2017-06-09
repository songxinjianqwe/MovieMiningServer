package me.newsong.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import me.newsong.cache.CacheManager;
import me.newsong.dao.MovieReviewDOMapper;
import me.newsong.dao.MovieTagDOMapper;
import me.newsong.dao.RemoteMovieInfoDOMapper;
import me.newsong.dao.UserDOMapper;
import me.newsong.dao.crawler.GrossCrawler;
import me.newsong.dao.crawler.IMDBCrawler;
import me.newsong.domain.common.MovieVO;
import me.newsong.domain.common.PredictedMovieDTO;
import me.newsong.domain.common.PredictedMovieVO;
import me.newsong.domain.entity.Movie;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.entity.MovieTagDO;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.domain.time.Day;
import me.newsong.domain.time.Month;
import me.newsong.enums.MovieReviewSortType;
import me.newsong.enums.MovieSortType;
import me.newsong.enums.TimeUnit;
import me.newsong.exception.DataSourceNotFoundException;
import me.newsong.exception.MovieNotFoundException;
import me.newsong.service.MovieService;
import me.newsong.util.JsonUtil;
import me.newsong.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
public class MovieServiceImpl extends MovieReviewTemplateImpl implements MovieService {
    @Autowired
    private MovieReviewDOMapper movieReviewDOMapper;
    @Autowired
    private RemoteMovieInfoDOMapper remoteMovieInfoDOMapper;
    @Autowired
    private MovieTagDOMapper movieTagDOMapper;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private UserDOMapper userDOMapper;
    @Autowired
    private IMDBCrawler imdbCrawler;
    @Autowired
    private GrossCrawler grossCrawler;

    @Override
    public List<String> findAllIds() {
        return movieReviewDOMapper.findAllMovieIds();
    }

    @Override
    public Movie findMovieByID(String id) {
        List<MovieReviewDO> reviews = findMovieReviewDOsSortedByTimeDesc(id);
        Movie movie = new Movie(id, reviews.get(reviews.size() - 1).getTime(), reviews.get(0).getTime(), reviews.size(),
                getVarianceOfScore(reviews), super.getKeyWords(reviews), getAverageScore(reviews));
        return movie;
    }

    /**
     * 只在一个地方抛出异常
     *
     * @param id
     * @return
     */
    @Override
    protected List<MovieReviewDO> findMovieReviewDOsById(String id) {
        List<MovieReviewDO> reviews = this.movieReviewDOMapper.findByMovieId(id);
        if (reviews.size() == 0) {
            throw new MovieNotFoundException(id);
        }
        return reviews;
    }

    @Override
    public double getVarianceOfScore(List<MovieReviewDO> reviews) {
        return getIntegerVariance(reviews.stream().map(MovieReviewDO::getScore).collect(Collectors.toList()));
    }

    /**
     * 计算Integer的平均数
     *
     * @param numbers
     * @return
     */
    private double avgInteger(Collection<Integer> numbers) {
        return numbers.stream().collect(Collectors.averagingDouble((num) -> 1.0 * num));
    }

    /**
     * 计算Double的平均数
     *
     * @param numbers
     * @return
     */
    private double avgDouble(Collection<Double> numbers) {
        return numbers.stream().collect(Collectors.averagingDouble((num) -> num));
    }

    /**
     * 平方的平均-平均的平方
     */
    private double getIntegerVariance(Collection<Integer> numbers) {
        long squareSum = numbers.stream()
                .collect(Collectors.summarizingInt((num) -> num * num)).getSum();
        double avg = avgInteger(numbers);
        return 1.0 * squareSum / numbers.size() - avg * avg;
    }

    /**
     * 平方的平均-平均的平方
     */
    private double getDoubleVariance(Collection<Double> numbers) {
        double squareSum = numbers.stream()
                .collect(Collectors.summingDouble((num) -> num * num));
        double avg = avgDouble(numbers);
        return 1.0 * squareSum / numbers.size() - avg * avg;
    }


    @Override
    public Map<Integer, Long> findScoresByStar(String id) {
        return findMovieReviewDOsById(id).stream()
                .collect(Collectors.groupingBy(MovieReviewDO::getScore, Collectors.counting()));
    }


    @Override
    public double getAverageScore(List<MovieReviewDO> reviews) {
        return reviews.stream().collect(Collectors.averagingDouble(MovieReviewDO::getScore));
    }


    @Override
    public List<String> findAllMovieNames() {
        return remoteMovieInfoDOMapper.findAllMovieNames();
    }

    private PageInfo<RemoteMovieInfoDO> addMovieStatistics(PageInfo<RemoteMovieInfoDO> infos) {
        for (RemoteMovieInfoDO info :
                infos.getList()) {
            info.getMovie().setReviewTimes(movieReviewDOMapper.countByMovieId(info.getMovieId()));
        }
        return infos;
    }

    @Override
    public PageInfo<RemoteMovieInfoDO> findMoviesByContainingName(String name, int pageNum, int pageSize) {
        final PageInfo<RemoteMovieInfoDO> moviesByContainingName = remoteMovieInfoDOMapper.findByNameContaining(name, pageNum, pageSize).toPageInfo();
        return addMovieStatistics(moviesByContainingName);
    }


    @Override
    public PageInfo<RemoteMovieInfoDO> findLatestMovies(int pageNum, int pageSize) {
        final PageInfo<RemoteMovieInfoDO> latestMovies = remoteMovieInfoDOMapper.findLatestMovies(pageNum, pageSize).toPageInfo();
        return addMovieStatistics(latestMovies);
    }

    @Override
    public PageInfo<RemoteMovieInfoDO> findMoviesByTag(String tag, MovieSortType sortBy, int pageNum, int pageSize) {
        if (sortBy == MovieSortType.Score) {
            return addMovieStatistics(remoteMovieInfoDOMapper.findByTagOrderByScore(tag, pageNum, pageSize).toPageInfo());
        } else if (sortBy == MovieSortType.Time) {
            return addMovieStatistics(remoteMovieInfoDOMapper.findByTagOrderByTime(tag, pageNum, pageSize).toPageInfo());
        }
        return null;
    }

    @Override
    public PageInfo<MovieReviewDO> findSortedMovieReviewsById(String id, MovieReviewSortType sort, int pageNum, int pageSize) {
        PageInfo<MovieReviewDO> page = null;
        if (sort == MovieReviewSortType.Helpful) {
            page = movieReviewDOMapper.findByIdOrderByHelpful(id, pageNum, pageSize).toPageInfo();
        } else if (sort == MovieReviewSortType.Time) {
            page = movieReviewDOMapper.findByIdOrderByTime(id, pageNum, pageSize).toPageInfo();
        }
        if (page == null) {
            return null;
        }
        for (MovieReviewDO movieReviewDO : page.getList()) {
            movieReviewDO.setUsername(userDOMapper.findByUserId(movieReviewDO.getUserId()).getUserName());
        }
        return page;
    }

    @Override
    public Map<String, Double> findMovieTagProportions() {

        int totalMovies = remoteMovieInfoDOMapper.count();
        Map<String, Double> result = new HashMap<>();
        for (MovieTagDO tag : movieTagDOMapper.findAll()) {
            result.put(tag.getTagName(), 1.0 * remoteMovieInfoDOMapper.countByTag(tag) / totalMovies);
        }
        return result;
    }

    @Override
    public Map<Integer, Set<Double>> findReviewTimesAndScores() {
        Map<Integer, Set<Double>> result = new HashMap<>();
        List<MovieReviewDO> reviews = null;
        for (String id : findAllIds()) {
            reviews = findMovieReviewDOsById(id);
            Integer reviewTimes = Integer.valueOf(reviews.size());
            if (!result.containsKey(reviewTimes)) {
                Set<Double> scores = new HashSet<>();
                scores.add(getAverageScore(reviews));
                result.put(reviewTimes, scores);
            } else {
                result.get(reviewTimes).add(getAverageScore(reviews));
            }
        }
        return result;
    }

    @Override
    public double getCorrelationCoefficientOfReviewTimesAndScores(Map<Integer, Set<Double>> map) {
        List<Integer> x = new ArrayList<>();
        List<Double> y = new ArrayList<>();
        List<Double> xy = new ArrayList<>();
        for (Map.Entry<Integer, Set<Double>> entry : map.entrySet()) {
            y.addAll(entry.getValue());
            for (Double d : entry.getValue()) {
                x.add(entry.getKey());
                xy.add(entry.getKey() * d);
            }
        }
        double cov = avgDouble(xy) - avgInteger(x) * avgDouble(y);
        return cov / Math.sqrt(getIntegerVariance(x)) * Math.sqrt(getDoubleVariance(y));
    }

    @Override
    public Map<Month, Double> findMovieScoresInMonthsById(String id) {
        return findMovieScores(TimeUnit.Month, findMovieReviewDOsById(id));
    }

    @Override
    public Map<Day, Double> findMovieScoresInDayByIdAndMonthSpan(String id, int monthSpan) {
        List<MovieReviewDO> reviews = super.findMovieReviewDOsSortedByTimeDesc(id);
        LocalDateTime begin = reviews.get(reviews.size() - 1).getTime();
        return findMovieScores(TimeUnit.Day, reviews.stream().filter((r) -> r.getTime().isBefore(begin.plusMonths(monthSpan)))
                .collect(Collectors.toList()));
    }

    @Override
    public List<String> findAllMovieTags() {
        return movieTagDOMapper.findAll().stream().map(MovieTagDO::getTagName).collect(Collectors.toList());
    }

    @Override
    public PageInfo<RemoteMovieInfoDO> findByCountryContaining(String country, int pageNum, int pageSize) {
        return remoteMovieInfoDOMapper.findByCountryContaining(country, pageNum, pageSize).toPageInfo();
    }

    @Override
    public PageInfo<RemoteMovieInfoDO> findByDirectorContaining(String director, int pageNum, int pageSize) {
        return remoteMovieInfoDOMapper.findByDirectorContaining(director, pageNum, pageSize).toPageInfo();
    }

    @Override
    public PageInfo<RemoteMovieInfoDO> findByLanguageContaining(String language, int pageNum, int pageSize) {
        return remoteMovieInfoDOMapper.findByLanguageContaining(language, pageNum, pageSize).toPageInfo();
    }

    @Override
    public PageInfo<RemoteMovieInfoDO> findByWriterContaining(String writer, int pageNum, int pageSize) {
        return remoteMovieInfoDOMapper.findByWriterContaining(writer, pageNum, pageSize).toPageInfo();
    }

    @Override
    public List<MovieVO> findDisplayMovies() {
        List<MovieVO> result = cacheManager.getList("findDisplayMovies", MovieVO.class);
        if (result == null) {
            result = remoteMovieInfoDOMapper.findAll().stream().map(MovieVO::new).collect(Collectors.toList());
            cacheManager.put("findDisplayMovies", result);
            return result;
        } else {
            return result;
        }
    }

    @Override
    public void addMovieReview(MovieReviewDO movieReviewDO) {
        movieReviewDO.setUserRecommendId(userDOMapper.findByUserId(movieReviewDO.getUserId()).getUserRecommendId());
        movieReviewDOMapper.insert(movieReviewDO);
    }

    @Override
    public List<RemoteMovieInfoDO> findInTheatersMovies() {
        try {
            return imdbCrawler.findInTheaterMovies();
        } catch (IOException e) {
            throw new DataSourceNotFoundException(IMDBCrawler.URL);
        }
    }

    @Override
    public PredictedMovieVO predict(String movieId) {
        PredictedMovieDTO predictedMovieDTO = imdbCrawler.crawlForPrediction(movieId);
        log.info("PredictedMovieDTO:{}", predictedMovieDTO);
        List<?> scoreParams = Arrays.asList(predictedMovieDTO.getMovie().getImdbReviewTime(), predictedMovieDTO.getBudget(), predictedMovieDTO.getNum_user_for_reviews(), predictedMovieDTO.getNum_critic_for_reviews(), predictedMovieDTO.getMovie().getDuration());
        Double score = util.call("predict_score", scoreParams, Double.class);
        log.info("score:{}", score);
        List<?> boxOfficeParams = Arrays.asList(predictedMovieDTO.getNum_critic_for_reviews(), predictedMovieDTO.getBudget(), predictedMovieDTO.getMovie().getImdbReviewTime());
        Double boxOffice = util.call("predict_box_office", boxOfficeParams, Double.class);
        log.info("boxOffice:{}", boxOffice);
        PredictedMovieVO vo = new PredictedMovieVO();
        vo.setMovie(predictedMovieDTO.getMovie());
        vo.setPredictedScore(score);
        vo.setPredictedBoxOffice((long) (boxOffice * 100000000));
        log.info("PredictedMovieVO:{}", vo);
        return vo;
    }

    @Override
    public Map<Integer, Long> predictWithHistory(int index) {
        Map<Integer, Long> history = grossCrawler.crawl(index);
        String json = util.call("predict_box_office_with_history", Arrays.asList(history), String.class);
        Map<Integer, Long> result = null;
        try {
            result = JsonUtil.getObjectMapper().readValue(json, new TypeReference<Map<Integer, Long>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    private <T> Map<T, Double> findMovieScores(TimeUnit unit, List<MovieReviewDO> reviews) {
        Map<T, List<MovieReviewDO>> midResult = reviews.stream().collect(Collectors.groupingBy(SpringContextUtil.getBean(unit.toString())));
        Map<T, Double> result = new TreeMap<>();
        List<MovieReviewDO> lastReviews = new ArrayList<>();
        for (Map.Entry<T, List<MovieReviewDO>> entry : midResult.entrySet()) {
            entry.getValue().addAll(lastReviews);
            result.put(entry.getKey(), entry.getValue().stream().collect(Collectors.averagingDouble((r) -> (double) r.getScore())));
            lastReviews = entry.getValue();
        }
        return result;
    }
}