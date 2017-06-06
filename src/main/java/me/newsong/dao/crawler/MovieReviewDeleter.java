package me.newsong.dao.crawler;

import me.newsong.dao.MovieReviewDOMapper;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by SinjinSong on 2017/6/6.
 */
public class MovieReviewDeleter extends BaseSpringTester{
    @Autowired
    private MovieReviewDOMapper movieReviewDOMapper;
    
    @Test
    public void removeDuplicated(){
        movieReviewDOMapper.findByMovieId("tt0185906").forEach(System.out::println);
        for (String movieId : movieReviewDOMapper.findAllMovieIds()) {
            Set<LocalDateTime> set = new HashSet<>();
            for (MovieReviewDO movieReviewDO : movieReviewDOMapper.findByMovieId(movieId)) {
                if(set.contains(movieReviewDO.getTime())){
                    movieReviewDOMapper.deleteByPrimaryKey(movieReviewDO.getId());
                }else{
                    set.add(movieReviewDO.getTime());
                }
            }
        }
    }
}
