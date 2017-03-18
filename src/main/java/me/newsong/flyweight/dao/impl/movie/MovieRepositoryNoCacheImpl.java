package me.newsong.flyweight.dao.impl.movie;

import me.newsong.flyweight.dao.iface.movie.MovieRepository;
import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.RemoteMovieInfo;
import me.newsong.flyweight.utils.PythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/18.
 */
@Repository("NoCacheMovies")
public class MovieRepositoryNoCacheImpl implements MovieRepository {
    private PythonUtil util = PythonUtil.getInstance();
    private Map<String, RemoteMovieInfo> moviesById;
    @Autowired
    @Qualifier("NoCacheReviews")
    private MovieReviewRepository dao;

    public MovieRepositoryNoCacheImpl() {
        moviesById = new HashMap<>();
    }
    
//    @PostConstruct
    public void init() {
        for (String id : dao.findAllMovieIds()) {
            moviesById.put(id, findMovieViaCrawler(id));
        }
    }
    
    @Override
    public RemoteMovieInfo findMovieViaCrawler(String id) {
        return util.call("crawl", Arrays.asList(id), RemoteMovieInfo.class);
    }
    
}
