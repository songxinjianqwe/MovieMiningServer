package me.newsong.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.newsong.util.movie.RemoteMovieInfoDTO;

/**
 * Created by SinjinSong on 2017/6/6.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredicteMovieVO {
    private RemoteMovieInfoDTO movie;
    private Double predictedScore;
    private Long predictedBoxOffice;
}
