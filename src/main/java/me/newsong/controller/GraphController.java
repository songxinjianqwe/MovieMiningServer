package me.newsong.controller;

import lombok.extern.slf4j.Slf4j;
import me.newsong.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@CrossOrigin
@RequestMapping("/graphs")
@RestController
@Slf4j
public class GraphController {
    @Autowired
    private GraphService service;
    
    @RequestMapping(value= "/hist_distribution_of_imdb_score",method = RequestMethod.GET,produces="application/json")
    public String  getHistDistributionOfImdbScore(@RequestParam("bins") Integer bins,@RequestParam("normed") Boolean normed){
        return service.getHistDistributionOfImdbScore(bins,normed);
    }
    
    @RequestMapping(value= "/hist_distribution_of_imdb_review_count",method = RequestMethod.GET,produces="application/json")
    public String  getHistDistributionOfImdbReviewCount(@RequestParam("bins") Integer bins,@RequestParam("normed") Boolean normed){
        return service.getHistDistributionOfImdbReviewCount(bins,normed);
    }
    
    @RequestMapping(value= "/cdf_of_imdb_score",method = RequestMethod.GET,produces="application/json")
    public String  getCdfOfImdbScore(){
        return service.getCdfOfImdbScore();
    }
    
    
    @RequestMapping(value= "/kde_of_imdb_score",method = RequestMethod.GET,produces="application/json")
    public String  getKdeOfImdbScore(){
        return service.getKdeOfImdbScore();
    }
    
    @RequestMapping(value= "/kde_of_imdb_review_count",method = RequestMethod.GET,produces="application/json")
    public String  getKdeOfImdbReviewCount(){
        return service.getKdeOfImdbReviewCount();
    }
    
    @RequestMapping(value= "/score_of_single_year",method = RequestMethod.GET,produces="application/json")
    public String  getScoreOfSingleYear(){
        return service.getScoreOfSingleYear();
    }
    
    @RequestMapping(value= "/box_office_of_single_year",method = RequestMethod.GET,produces="application/json")
    public String  getBoxOfficeOfSingleYear(){
        return service.getBoxOfficeOfSingleYear();
    }
    
}
