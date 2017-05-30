package me.newsong.domain.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * Created by SinjinSong on 2017/5/29.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieReviewDTO {
    @NotNull
    private String movieId;
    @NotNull
    private Integer score;
    @NotNull
    private String summary;
    @NotNull
    private String content;
}
