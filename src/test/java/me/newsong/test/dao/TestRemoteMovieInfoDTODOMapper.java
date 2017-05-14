package me.newsong.test.dao;

import me.newsong.dao.RemoteMovieInfoDOMapper;
import me.newsong.domain.entity.MovieTagDO;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by SinjinSong on 2017/4/23.
 */
public class TestRemoteMovieInfoDTODOMapper extends BaseSpringTester{
    @Autowired
    private RemoteMovieInfoDOMapper mapper;
    
    @Test
    public void findByNameContainingTest(){
        mapper.findByNameContaining("the",2,5).forEach(System.out::println);
    }
    
    @Test
    public void findAllMovieNamesTest(){
        mapper.findAllMovieNames().forEach(System.out::println);    
    }
    
    @Test
    public void findLatestMoviesTest(){
        mapper.findLatestMovies(2,5).forEach(System.out::println);    
    }
    
    @Test
    public void findByTagOrderByScoreTest(){
        mapper.findByTagOrderByScore("Romance",3,60).forEach(System.out::println);    
    }
    
    @Test
    public void findByTagOrderByTimeTest(){
        mapper.findByTagOrderByTime("Romance",3,20).forEach(System.out::println);    
    }
    
    @Test
    public void countByTagTest(){
        System.out.println(mapper.countByTag(new MovieTagDO(1L,null)));
    }
    
    @Test
    public void countTest(){
        System.out.println(mapper.count());
    }
    
    @Test
    public void findByMovieIdContainingTest(){
        mapper.findByMovieIdContaining("B00",2,10).forEach(System.out::println);
    }
    
    @Test
    public void findByCountryContainingTest(){
        mapper.findByCountryContaining("u",2,10).forEach(System.out::println);
    }
    
    @Test
    public void findByDirectorContainingTest(){
        mapper.findByDirectorContaining("t",2,20).forEach(System.out::println);
    }
    
    @Test
    public void findByLanguageContainingTest(){
        mapper.findByLanguageContaining("mandarin",2,20).forEach(System.out::println);
    }
    
    @Test
    public void findByWriterContainingTest(){
        mapper.findByWriterContaining("t",2,20).forEach(System.out::println);
    }
    
}
