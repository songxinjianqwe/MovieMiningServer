package me.newsong.domain.common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import me.newsong.domain.entity.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by SinjinSong on 2017/5/17.
 */
public class SpecilaMovieDTO implements Serializable{
    private String movieId;
    private String posterURL;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDate releaseTime;
    private Integer doubanReviewTime;
    private Integer imdbReviewTime;
    private Double doubanScore;
    private Integer gross;
    private Integer duration;
    private List<String> directors;
    private List<String> writers;
    private List<String> actors;
    private List<String> languages;
    private List<String> countries;
    private List<String> tags;
    private Double imdbScore;
    private String summary;
    
    public SpecilaMovieDTO(RemoteMovieInfoDO remoteMovieInfoDO){
        this.movieId = remoteMovieInfoDO.getMovieId();
        this.posterURL = remoteMovieInfoDO.getPosterUrl();
        this.name = remoteMovieInfoDO.getName();
        this.releaseTime = remoteMovieInfoDO.getReleaseTime();
        this.doubanReviewTime = remoteMovieInfoDO.getDoubanReviewTime();
        this.imdbReviewTime = remoteMovieInfoDO.getImdbReviewTime();
        this.doubanScore = remoteMovieInfoDO.getDoubanScore();
        this.gross = remoteMovieInfoDO.getGross();
        this.duration = remoteMovieInfoDO.getDuration();
        this.directors = remoteMovieInfoDO.getDirectors().stream().map(DirectorDO::getDirectorName).collect(Collectors.toList());
        this.writers = remoteMovieInfoDO.getWriters().stream().map(WriterDO::getWriterName).collect(Collectors.toList());
        this.actors = remoteMovieInfoDO.getActors().stream().map(ActorDO::getActorName).collect(Collectors.toList());
        this.languages = remoteMovieInfoDO.getLanguages().stream().map(LanguageDO::getLanguageName).collect(Collectors.toList());
        this.countries = remoteMovieInfoDO.getCountries().stream().map(CountryDO::getCountryName).collect(Collectors.toList());
        this.tags = remoteMovieInfoDO.getTags().stream().map(MovieTagDO::getTagName).collect(Collectors.toList());
        this.imdbScore = remoteMovieInfoDO.getImdbScore();
        this.summary = remoteMovieInfoDO.getSummary();
    }

    public String getMovieId() {
        return movieId;
    }

    public String getPosterURL() {
        return posterURL;
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

    public List<String> getDirectors() {
        return directors;
    }

    public List<String> getWriters() {
        return writers;
    }

    public List<String> getActors() {
        return actors;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public List<String> getCountries() {
        return countries;
    }

    public List<String> getTags() {
        return tags;
    }

    public Double getImdbScore() {
        return imdbScore;
    }

    public String getSummary() {
        return summary;
    }
}
