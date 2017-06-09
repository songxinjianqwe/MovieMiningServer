package me.newsong.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import me.newsong.domain.entity.RemoteMovieInfoDO;

import java.time.LocalDateTime;

/**
 * Created by SinjinSong on 2017/6/1.
 */
@Data
public class MovieReviewVO {
    private RemoteMovieInfoDO movie;
    private Integer score;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime reviewTime;
}
