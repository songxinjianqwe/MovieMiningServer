package me.newsong.service;

import me.newsong.domain.common.PredictedMovieVO;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by SinjinSong on 2017/6/7.
 */
public class MovieServiceTest extends BaseSpringTester{
    @Autowired
    private MovieService service;
    @Test
    public void predict() throws Exception {
        PredictedMovieVO movie = service.predict("tt5655222");
        System.out.println(movie);
    }

}