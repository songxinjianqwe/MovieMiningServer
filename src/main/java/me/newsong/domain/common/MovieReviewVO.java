package me.newsong.domain.common;

import lombok.Data;
import me.newsong.domain.entity.RemoteMovieInfoDO;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@Data
public class MovieReviewVO {
    private RemoteMovieInfoDO movie;
    private Integer score;
}
