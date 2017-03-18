package me.newsong.flyweight.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.newsong.flyweight.dao.iface.movie.MovieRepository;
import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.Movie;
import me.newsong.flyweight.domain.RemoteMovieInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/3/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class RemoteMovieSaver {
    @Autowired
    @Qualifier("NoCacheMovies")
    private MovieRepository movieDao;
    @Autowired
    @Qualifier("CachedReviews")
    private MovieReviewRepository reviewDao;

    @Test
    public void save() throws IOException {
        Map<String, RemoteMovieInfo> movies = new HashMap<>();
        List<String> ids = reviewDao.findAllMovieIds();
        for (int i = 0; i < ids.size(); ++i) {
            System.out.println(i);
            RemoteMovieInfo movie = movieDao.findMovieViaCrawler(ids.get(i));
            
            if(movie != null && movie.getPosterURL() != null){
                movies.put(ids.get(i), movie);
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        FileWriter writer = new FileWriter("E:/movies.json");
        writer.write(mapper.writeValueAsString(movies));
        writer.close();
    }
}
