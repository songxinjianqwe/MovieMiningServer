package me.newsong.controller;

import lombok.extern.slf4j.Slf4j;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.security.domain.JWTUser;
import me.newsong.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@RestController
@CrossOrigin
@RequestMapping("/recommend")
@Slf4j
public class RecommendController {
    @Autowired
    private RecommendService recommendService;
    
    @RequestMapping(value = "/users",method = RequestMethod.GET)
    public List<RemoteMovieInfoDO> recommendForUser(@AuthenticationPrincipal JWTUser user){
        return recommendService.recommendForUser(user.getUser().getUserRecommendId());
    }
    
    @RequestMapping(value = "/movies/{id}",method = RequestMethod.GET)
    public List<RemoteMovieInfoDO> findSimilarMovies(@PathVariable("id") String movieId){
        return recommendService.findSimilarMovies(movieId);
    }
}
