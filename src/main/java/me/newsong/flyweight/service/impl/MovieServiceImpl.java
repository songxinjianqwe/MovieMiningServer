package me.newsong.flyweight.service.impl;

import me.newsong.flyweight.dao.iface.movie.MovieRepository;
import me.newsong.flyweight.domain.entity.Movie;
import me.newsong.flyweight.domain.entity.MovieReview;
import me.newsong.flyweight.domain.entity.PageBean;
import me.newsong.flyweight.domain.entity.RemoteMovieInfo;
import me.newsong.flyweight.domain.time.Month;
import me.newsong.flyweight.enums.MovieSortType;
import me.newsong.flyweight.enums.MovieTag;
import me.newsong.flyweight.exceptions.MovieNotFoundException;
import me.newsong.flyweight.service.iface.MovieService;
import me.newsong.flyweight.service.impl.comp.RemoteMovieInfoScoreDescComparator;
import me.newsong.flyweight.service.impl.comp.RemoteMovieInfoTimeDescComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class MovieServiceImpl extends MovieReviewTemplateImpl implements MovieService {
    private Map<String, List<RemoteMovieInfo>> moviesByName;
    private Map<MovieTag, List<RemoteMovieInfo>> moviesByTagOrderedByScore;
    private Map<MovieTag, List<RemoteMovieInfo>> moviesByTagOrderedByTime;
    private List<RemoteMovieInfo> movieSortedByTime;
    private int pageSize;
    @Autowired
    @Qualifier("CachedMovies")
    private MovieRepository movieDao;

    public MovieServiceImpl() {
        pageSize = Integer.parseInt(ResourceBundle.getBundle("page").getString("pageSize"));
        moviesByName = new ConcurrentHashMap<>();
        moviesByTagOrderedByScore = new ConcurrentHashMap<>();
        moviesByTagOrderedByTime = new ConcurrentHashMap<>();
        movieSortedByTime = new ArrayList<>();
    }

    @PostConstruct
    private void init() {
        System.out.println("开始初始化MovieService...");
        RemoteMovieInfo movie = null;
        for (String id : findAllIds()) {
            movie = movieDao.findMovieViaCrawler(id);
            if (movie == null) {
                continue;
            }
            movie.setId(id);
            movie.setMovieAvgScore(getAverageScore(findMovieReviewById(id)));
            String movieName = movie.getName();
            List<RemoteMovieInfo> list = null;
            if (moviesByName.get(movieName) == null) {
                list = new ArrayList<>();
                list.add(movie);
                moviesByName.put(movieName, list);
            } else if (!moviesByName.get(movieName).contains(movie)) {
//                System.out.println("同名新电影");
//                moviesByName.get(movieName).forEach(System.out::println);
//                System.out.println(movie);
                moviesByName.get(movieName).add(movie);
            } else {
//                System.out.println("已存在该电影 "+movieName);
                continue;
            }
            for (MovieTag tag : movie.getTags()) {
                putMovieIntoTagMap(tag, movie);
            }
            movieSortedByTime.add(movie);
        }
        for (Map.Entry<MovieTag, List<RemoteMovieInfo>> entry : moviesByTagOrderedByScore.entrySet()) {
            Collections.sort(entry.getValue(), new RemoteMovieInfoScoreDescComparator());
            List<RemoteMovieInfo> movies = new ArrayList<>(entry.getValue());
            Collections.sort(movies, new RemoteMovieInfoTimeDescComparator());
            moviesByTagOrderedByTime.put(entry.getKey(), movies);
        }
        Collections.sort(movieSortedByTime, new RemoteMovieInfoTimeDescComparator());

        System.out.println("MovieService初始化完毕！");
//        System.out.println(movieSortedByTime.size());
//        for (Map.Entry<MovieTag, List<RemoteMovieInfo>> entry : moviesByTagOrderedByTime.entrySet()) {
//            System.out.println(entry.getKey());
//            for(RemoteMovieInfo info:entry.getValue()){
//                System.out.println(info.getName()+"  "+ info.getReleaseTime());
//            }
//        }
//        System.out.println(moviesByName);
//        System.out.println(moviesByTagOrderedByScore);
//        System.out.println(moviesByTagOrderedByTime);
//        movieSortedByTime.forEach(System.out::println);
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
            throw new MovieNotFoundException(id);
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
        return 1.0 * squareSum / reviews.size() - avg * avg;
    }

    @Override
    public Map<Integer, Long> findScoresByStar(String id) {
        return findMovieReviewById(id).stream()
                .collect(Collectors.groupingBy(MovieReview::getScore, Collectors.counting()));
    }


    @Override
    public double getAverageScore(List<MovieReview> reviews) {
        return reviews.stream().collect(Collectors.averagingDouble(MovieReview::getScore));
    }

    private void putMovieIntoTagMap(MovieTag tag, RemoteMovieInfo movie) {
        if (moviesByTagOrderedByScore.get(tag) == null) {
            List<RemoteMovieInfo> list = new ArrayList<>();
            list.add(movie);
            moviesByTagOrderedByScore.put(tag, list);
        } else {
            moviesByTagOrderedByScore.get(tag).add(movie);
        }
    }

    @Override
    public List<String> findAllMovieNames() {
        return new ArrayList<>(moviesByName.keySet());
    }

    private PageBean<RemoteMovieInfo> getPageBean(int currPage, List<RemoteMovieInfo> infos) {
        PageBean<RemoteMovieInfo> pageBean = new PageBean<>(currPage, infos.size(), this.pageSize);
        List<RemoteMovieInfo> currPageContents = getCopiedSubList(infos, pageBean.getCurrPageBeginIndex(), pageBean.getCurrPageEndIndex());
        for (RemoteMovieInfo info : currPageContents) {
            info.setMovie(findMovieByID(info.getId()));
        }
        pageBean.setData(currPageContents);
        return pageBean;
    }

    private <T> List<T> getCopiedSubList(List<T> list, int begin, int end) {
        List<T> result = new ArrayList<T>();
        for (int i = begin; i < end; ++i) {
            result.add(list.get(i));
        }
//        System.out.println("子列长度"+result.size());
        return result;
    }

    @Override
    public PageBean<RemoteMovieInfo> findMoviesByName(String name, int currPage) {
        if (!moviesByName.containsKey(name)) {
            throw new MovieNotFoundException(name);
        }
        return getPageBean(currPage, moviesByName.get(name));
    }

    @Override
    public PageBean<RemoteMovieInfo> findMoviesByNames(String[] names, int currPage) {
        List<RemoteMovieInfo> list = new ArrayList<>();
        for (String name : names) {
            if (!moviesByName.containsKey(name)) {
                throw new MovieNotFoundException(name);
            }
            list.addAll(moviesByName.get(name));
        }
        return getPageBean(currPage, list);
    }


    @Override
    public PageBean<RemoteMovieInfo> findLatestMovies(int currPage) {
        return getPageBean(currPage, movieSortedByTime);
    }

    @Override
    public PageBean<RemoteMovieInfo> findMoviesByTag(MovieTag tag, MovieSortType sortBy, int currPage) {
        if (sortBy == MovieSortType.Score) {
            return getPageBean(currPage, moviesByTagOrderedByScore.get(tag));
        } else if (sortBy == MovieSortType.Time) {
            return getPageBean(currPage, moviesByTagOrderedByTime.get(tag));
        }
        return null;
    }

    @Override
    public List<MovieReview> findTop10MovieReviewsById(String id) {
        return null;
    }

    @Override
    public Map<MovieTag, Double> findMovieTagProportions() {
        return null;
    }

    @Override
    public Map<Long, Double> findReviewTimesAndScores() {
        return null;
    }

    @Override
    public Map<Month, Double> findMovieScoresInMonthsById(String id) {
        return null;
    }

    @Override
    public Map<Date, Double> findMovieScoresInDayByIdAndMonthSpan(String id, int monthSpan) {
        return null;
    }
}
