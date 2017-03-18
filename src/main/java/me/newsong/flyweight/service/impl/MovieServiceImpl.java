package me.newsong.flyweight.service.impl;

import me.newsong.flyweight.dao.iface.movie.MovieRepository;
import me.newsong.flyweight.domain.Movie;
import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.enums.MovieTag;
import me.newsong.flyweight.exceptions.MovieNotFoundException;
import me.newsong.flyweight.service.iface.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class MovieServiceImpl extends BaseMovieReviewHandler implements MovieService {
    private Map<String, List<Movie>> moviesByName;
    private Map<MovieTag, List<Movie>> moviesByTag;
    private List<Movie> movies;

    @Autowired
    @Qualifier("NoCacheMovies")
    private MovieRepository movieDao;

    public MovieServiceImpl() {
        moviesByName = new ConcurrentHashMap<>();
        moviesByTag = new ConcurrentHashMap<>();
        movies = new ArrayList<>();
    }

//    @PostConstruct
    private void init() {
        for (String id : findAllIds()) {
            Movie movie = findMovieByID(id);
            movie.setRemoveInfo(movieDao.findMovieViaCrawler(id));
            String movieName = movie.getRemoveInfo().getName();
            List<Movie> list = null;
            if (moviesByName.get(movieName) == null) {
                list = new ArrayList<>();
                list.add(movie);
                moviesByName.put(movieName, list);
            } else {
                moviesByName.get(movieName).add(movie);
            }
            for (MovieTag tag : movie.getRemoveInfo().getTags()) {
                putMovieIntoTagMap(tag, movie);
            }
            movies.add(movie);
        }
        Collections.sort(movies, (m1, m2) -> {
            if (m1.getRemoveInfo().getReleaseTime().before(m2.getRemoveInfo().getReleaseTime())) {
                return 1;
            } else if (m1.getRemoveInfo().getReleaseTime().after(m2.getRemoveInfo().getReleaseTime())) {
                return -1;
            } else {
                return 0;
            }
        });
        System.out.println(moviesByName);
        System.out.println(moviesByTag);
        System.out.println(movies);
    }


    @Override
    public List<String> findAllIds() {
        return dao.findAllMovieIds();
    }

    @Override
    public Movie findMovieByID(String id) {
        List<MovieReview> reviews = findMovieReviewsSortedByTime(id);
        Movie movie = new Movie(id, reviews.get(0).getTime(), reviews.get(reviews.size() - 1).getTime(), reviews.size(),
                getVarianceOfScore(reviews), super.getKeyWords(reviews), getAverageScore(reviews));
        return movie;
    }

    /**
     * 只在一个地方抛出异常
     *
     * @param id
     * @return
     */
    protected List<MovieReview> findMovieReviewById(String id) {
        List<MovieReview> reviews = dao.findByMovieId(id);
        if (reviews.size() == 0) {
            throw new MovieNotFoundException(id, null);
        }
        return reviews;
    }

    /**
     * 平方的平均-平均的平方
     */
    @Override
    public double getVarianceOfScore(List<MovieReview> reviews) {
        long squareSum = reviews.stream()
                .collect(Collectors.summarizingInt((review) -> review.getScore() * review.getScore())).getSum();
        double avg = reviews.stream().collect(Collectors.averagingDouble(MovieReview::getScore));
        // System.out.println("SquareSum "+squareSum);
        // System.out.println("avg "+avg);
        return 1.0 * squareSum / reviews.size() - avg * avg;
    }

    @Override
    public Map<Integer, Long> findScoresByStar(String id) {
        return findMovieReviewById(id).stream()
                .collect(Collectors.groupingBy(MovieReview::getScore, Collectors.counting()));
    }

    @Override
    public List<Movie> findMoviesByNames(String[] names) {

        List<Movie> movies = new ArrayList<>();
        for (String name : names) {
            movies.addAll(moviesByName.get(name));
        }
        return movies;
    }

    @Override
    public double getAverageScore(List<MovieReview> reviews) {
        return reviews.stream().collect(Collectors.averagingDouble(MovieReview::getScore));
    }

    private void putMovieIntoTagMap(MovieTag tag, Movie movie) {
        if (moviesByTag.get(tag) == null) {
            List<Movie> list = new ArrayList<>();
            list.add(movie);
            moviesByTag.put(tag, list);
        } else {
            moviesByTag.get(tag).add(movie);
        }
    }
}
