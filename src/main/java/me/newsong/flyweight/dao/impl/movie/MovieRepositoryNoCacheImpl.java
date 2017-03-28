package me.newsong.flyweight.dao.impl.movie;

import me.newsong.flyweight.dao.iface.movie.MovieRepository;
import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.entity.RemoteMovieInfo;
import me.newsong.flyweight.utils.PythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/18.
 */
@Repository("NoCacheMovies")
public class MovieRepositoryNoCacheImpl implements MovieRepository {
    private PythonUtil util ;
    private Map<String, RemoteMovieInfo> moviesById;
    @Autowired
    @Qualifier("NoCacheReviews")
    private MovieReviewRepository dao;

    public MovieRepositoryNoCacheImpl() {
        util = PythonUtil.getInstance();
        moviesById = new HashMap<>();
    }
    
//    @PostConstruct
    private void init() {
        for (String id : dao.findAllMovieIds()) {
            moviesById.put(id, findMovieViaCrawler(id));
        }
        moviesById = Collections.unmodifiableMap(moviesById);
    }
    
    @Override
    public RemoteMovieInfo findMovieViaCrawler(String id) {
        System.out.println("连接Python服务器启动爬虫");
        return util.call("crawl", Arrays.asList(id), RemoteMovieInfo.class);
    }
    
}
