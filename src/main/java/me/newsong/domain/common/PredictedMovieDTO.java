package me.newsong.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.newsong.domain.entity.RemoteMovieInfoDO;

/**
 * Created by SinjinSong on 2017/6/6.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictedMovieDTO {
    private RemoteMovieInfoDO movie;
    private Integer num_user_for_reviews;
    private Integer num_critic_for_reviews;
    private Long budget;
    
}
