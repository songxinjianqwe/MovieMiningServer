package me.newsong.util;

import me.newsong.dao.RemoteMovieInfoDOMapper;
import me.newsong.domain.common.MovieReviewVO;
import me.newsong.domain.entity.MovieReviewDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@Component
public class MovieReviewConverter implements POVOConverter<MovieReviewDO, MovieReviewVO> {
    @Autowired
    private RemoteMovieInfoDOMapper remoteMovieInfoDOMapper;
    
    @Override
    public MovieReviewVO apply(MovieReviewDO movieReviewDO) {
        MovieReviewVO vo = new MovieReviewVO();
        vo.setScore(movieReviewDO.getScore());
        vo.setReviewTime(movieReviewDO.getTime());
        vo.setMovie(remoteMovieInfoDOMapper.findByRecommendId(movieReviewDO.getMovieRecommendId()));
        return vo;
    }
}
