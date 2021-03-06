package me.newsong.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class Movie {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime earliestReviewTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime latestReviewTime;
    private int reviewTimes;
    private double varianceOfScore;
    private List<String> keyWords;
    private double avgScore;


    public Movie() {
    }

    public Movie(String id, LocalDateTime earliestReviewTime, LocalDateTime latestReviewTime, int reviewTimes, double varianceOfScore, List<String> keyWords, double avgScore) {
        this.id = id;
        this.earliestReviewTime = earliestReviewTime;
        this.latestReviewTime = latestReviewTime;
        this.reviewTimes = reviewTimes;
        this.varianceOfScore = varianceOfScore;
        this.keyWords = keyWords;
        this.avgScore = avgScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEarliestReviewTime() {
        return earliestReviewTime;
    }

    public void setEarliestReviewTime(LocalDateTime earliestReviewTime) {
        this.earliestReviewTime = earliestReviewTime;
    }

    public LocalDateTime getLatestReviewTime() {
        return latestReviewTime;
    }

    public void setLatestReviewTime(LocalDateTime latestReviewTime) {
        this.latestReviewTime = latestReviewTime;
    }

    public int getReviewTimes() {
        return reviewTimes;
    }

    public void setReviewTimes(int reviewTimes) {
        this.reviewTimes = reviewTimes;
    }

    public double getVarianceOfScore() {
        return varianceOfScore;
    }

    public void setVarianceOfScore(double varianceOfScore) {
        this.varianceOfScore = varianceOfScore;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", earliestReviewTime=" + earliestReviewTime +
                ", latestReviewTime=" + latestReviewTime +
                ", reviewTimes=" + reviewTimes +
                ", varianceOfScore=" + varianceOfScore +
                ", keyWords=" + keyWords +
                ", avgScore=" + avgScore +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (reviewTimes != movie.reviewTimes) return false;
        if (Double.compare(movie.varianceOfScore, varianceOfScore) != 0) return false;
        if (Double.compare(movie.avgScore, avgScore) != 0) return false;
        if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
        if (earliestReviewTime != null ? !earliestReviewTime.equals(movie.earliestReviewTime) : movie.earliestReviewTime != null)
            return false;
        if (latestReviewTime != null ? !latestReviewTime.equals(movie.latestReviewTime) : movie.latestReviewTime != null)
            return false;
        return keyWords != null ? keyWords.equals(movie.keyWords) : movie.keyWords == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (earliestReviewTime != null ? earliestReviewTime.hashCode() : 0);
        result = 31 * result + (latestReviewTime != null ? latestReviewTime.hashCode() : 0);
        result = 31 * result + reviewTimes;
        temp = Double.doubleToLongBits(varianceOfScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (keyWords != null ? keyWords.hashCode() : 0);
        temp = Double.doubleToLongBits(avgScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}   
