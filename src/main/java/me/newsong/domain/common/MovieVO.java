package me.newsong.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import me.newsong.domain.entity.CountryDO;
import me.newsong.domain.entity.DirectorDO;
import me.newsong.domain.entity.MovieTagDO;
import me.newsong.domain.entity.RemoteMovieInfoDO;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SinjinSong on 2017/5/17.
 */

public class MovieVO implements Serializable{
    private String movieId;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate releaseTime;
    private Integer doubanReviewTime;
    private Integer imdbReviewTime;
    private Double doubanScore;
    private Integer gross;
    private Integer duration;
    private List<String> tags;
    private Double imdbScore;
    private List<String> countries;
    private List<String> directories;
    
    public MovieVO(RemoteMovieInfoDO remoteMovieInfoDO){
        this.movieId = remoteMovieInfoDO.getMovieId();
        this.name = remoteMovieInfoDO.getName();
        this.releaseTime = remoteMovieInfoDO.getReleaseTime();
        this.doubanReviewTime = remoteMovieInfoDO.getDoubanReviewTime();
        this.imdbReviewTime = remoteMovieInfoDO.getImdbReviewTime();
        this.doubanScore = remoteMovieInfoDO.getDoubanScore();
        this.gross = remoteMovieInfoDO.getGross();
        this.duration = remoteMovieInfoDO.getDuration();
        this.tags = remoteMovieInfoDO.getTags().stream().map(MovieTagDO::getTagName).collect(Collectors.toList());
        this.imdbScore = remoteMovieInfoDO.getImdbScore();
        this.countries = remoteMovieInfoDO.getCountries().stream().map(CountryDO::getCountryName).collect(Collectors.toList());
        this.directories = remoteMovieInfoDO.getDirectors().stream().map(DirectorDO::getDirectorName).collect(Collectors.toList());
    }

    public String getMovieId() {
        return movieId;
    }


    public String getName() {
        return name;
    }

    public LocalDate getReleaseTime() {
        return releaseTime;
    }

    public Integer getDoubanReviewTime() {
        return doubanReviewTime;
    }

    public Integer getImdbReviewTime() {
        return imdbReviewTime;
    }

    public Double getDoubanScore() {
        return doubanScore;
    }

    public Integer getGross() {
        return gross;
    }

    public Integer getDuration() {
        return duration;
    }


    public List<String> getTags() {
        return tags;
    }

    public Double getImdbScore() {
        return imdbScore;
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getDirectories() {
        return directories;
    }
}
