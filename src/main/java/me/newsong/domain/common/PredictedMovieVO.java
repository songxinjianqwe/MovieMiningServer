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
public class PredictedMovieVO {
    private RemoteMovieInfoDO movie;
    private Double predictedScore;
    private Long predictedBoxOffice;
}
