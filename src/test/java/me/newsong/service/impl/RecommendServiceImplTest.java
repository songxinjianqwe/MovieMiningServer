package me.newsong.service.impl;

import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.service.MovieService;
import me.newsong.service.RecommendService;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by SinjinSong on 2017/6/1.
 */
public class RecommendServiceImplTest extends BaseSpringTester {
    @Autowired
    private RecommendService recommendService;
    @Autowired
    private MovieService movieService;
    @Test
    public void recommendForUser() throws Exception {
        
        List<RemoteMovieInfoDO> movies = recommendService.recommendForUser(1L);
        movies.forEach(System.out::println);
    }
    
    @Test   
    public void findSimilarMovies() throws Exception {
    }

}