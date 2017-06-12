package me.newsong.service.impl;

import lombok.extern.slf4j.Slf4j;
import me.newsong.dao.RemoteMovieInfoDOMapper;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.exception.MovieNotFoundException;
import me.newsong.exception.PythonServerErrorException;
import me.newsong.service.RecommendService;
import me.newsong.util.PythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@Service
@Slf4j
public class RecommendServiceImpl implements RecommendService{
    private PythonUtil pythonUtil = PythonUtil.getInstance();
    @Autowired
    private RemoteMovieInfoDOMapper remoteMovieInfoDOMapper;
    
    @Override
    public List<RemoteMovieInfoDO> recommendForUser(Long userRecommendId) {
        List<Long> movieRecommendIds = pythonUtil.callForRawList("recommendForUser", Arrays.asList(userRecommendId), Long.class);
        
        return remoteMovieInfoDOMapper.findByRecommendIds(movieRecommendIds);
    }
    
    @Override
    public List<RemoteMovieInfoDO> findSimilarMovies(String movieId) {
        RemoteMovieInfoDO movie = remoteMovieInfoDOMapper.findByMovieId(movieId);
        if(movie == null){
            throw new MovieNotFoundException(movieId);
        }
        Long movieRecommendId = movie.getMovieRecommendId();
        log.info("movieRecommendId:{}",movieRecommendId);
        List<Long> movieRecommendIds = pythonUtil.callForRawList("getSimilarMovies", Arrays.asList(movieRecommendId), Long.class);
        if(movieRecommendIds.size() == 0){
            throw new PythonServerErrorException();
        }
        List<RemoteMovieInfoDO> result = remoteMovieInfoDOMapper.findByRecommendIds(movieRecommendIds);
        log.info("{}",result.size());
        return result;
    }
}
