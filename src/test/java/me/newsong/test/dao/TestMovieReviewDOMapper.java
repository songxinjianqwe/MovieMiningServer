package me.newsong.test.dao;

import me.newsong.dao.MovieReviewDOMapper;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by SinjinSong on 2017/4/23.
 */
public class TestMovieReviewDOMapper extends BaseSpringTester{
    @Autowired
    private MovieReviewDOMapper mapper;
    @Test
    public void findAllMovieIdsTest(){
        mapper.findAllMovieIds().forEach(System.out::println);
    }
    
    @Test
    public void findAllUserIdsTest(){
        mapper.findAllUserIds().forEach(System.out::println);    
    }
    
    @Test
    public void findByMovieIdTest(){
        mapper.findByMovieId("B000MMMT9Q").forEach(System.out::println);
    }
    
    @Test
    public void findByUserIdTest(){
        mapper.findByUserId("A1FY8NOC0ZQ3LI").forEach(System.out::println);
    }
    
    @Test
    public void findByIdOrderByHelpfulTest(){
        mapper.findByIdOrderByHelpful("tt0114709",2,5).forEach(System.out::println);
        
    }
    
    @Test
    public void findByIdOrderByTimeTest(){
        mapper.findByIdOrderByTime("B000MMMT9Q",3,5).forEach(System.out::println);    
    }
    
    @Test
    public void countByMovieIdTest(){
        System.out.println(mapper.countByMovieId("B000MMMT9Q"));
    }
    
    @Test
    public void test(){
        for (int i  = 0; i < 4; i++) {
            mapper.findByUserRecommendId(2L,i+1,5).forEach(System.out::println);
            System.out.println();
        }
        
        
    }
}
