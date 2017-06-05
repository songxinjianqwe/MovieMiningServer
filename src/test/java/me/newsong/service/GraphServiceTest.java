package me.newsong.service;

import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by SinjinSong on 2017/6/2.
 */
public class GraphServiceTest extends BaseSpringTester{
    @Autowired
    private GraphService service;
    
    @Test
    public void getHistDistributionOfImdbScore() throws Exception {
//        String histDistributionOfImdbScore = service.getHistDistributionOfImdbScore(20, true);
//        System.out.println(histDistributionOfImdbScore);
//        Map<Double, Double> cdf = service.getKdeOfImdbScore();
//        System.out.println(cdf);
        Map<Integer, Double> kdeOfImdbReviewCount = service.getKdeOfImdbReviewCount();
        System.out.println(kdeOfImdbReviewCount);
    }

    @Test
    public void getHistDistributionOfImdbReviewCount() throws Exception {
    }

}