package me.newsong.service;

import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by SinjinSong on 2017/6/2.
 */
public class GraphServiceTest extends BaseSpringTester{
    @Autowired
    private GraphService service;
    
    @Test
    public void getHistDistributionOfImdbScore() throws Exception {
        String histDistributionOfImdbScore = service.getHistDistributionOfImdbScore(20, true);
        System.out.println(histDistributionOfImdbScore);
    }

    @Test
    public void getHistDistributionOfImdbReviewCount() throws Exception {
    }

}