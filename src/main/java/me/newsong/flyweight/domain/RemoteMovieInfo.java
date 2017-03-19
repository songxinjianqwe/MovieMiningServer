package me.newsong.flyweight.domain;

import me.newsong.flyweight.enums.MovieTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Created by SinjinSong on 2017/3/18.
 */
public class RemoteMovieInfo {
    private String id;
    private String posterURL;
    private String name;
    private MovieTag[] tags;
    private Date releaseTime;
    private String[] directors;
    private String[] writers;
    private String[] actors;
    private String[] languages;
    private String[] countries;
    private String summary;
    private double imdbScore;
    private int duration;
    private Movie movie = new Movie();

    public RemoteMovieInfo() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MovieTag[] getTags() {
        return tags;
    }

    public void setTags(MovieTag[] tags) {
        this.tags = tags;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String[] getDirectors() {
        return directors;
    }

    public void setDirectors(String[] directors) {
        this.directors = directors;
    }

    public String[] getWriters() {
        return writers;
    }

    public void setWriters(String[] writers) {
        this.writers = writers;
    }

    public String[] getActors() {
        return actors;
    }

    public void setActors(String[] actors) {
        this.actors = actors;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setMovieAvgScore(double avgScore) {
        this.movie.setAvgScore(avgScore);
    }

    @Override
    public String toString() {
        return "RemoteMovieInfo{" +
                "id=" + id +
                ", posterURL='" + posterURL + '\'' +
                ", name='" + name + '\'' +
                ", tags=" + Arrays.toString(tags) +
                ", releaseTime=" + releaseTime +
                ", directors=" + Arrays.toString(directors) +
                ", writers=" + Arrays.toString(writers) +
                ", actors=" + Arrays.toString(actors) +
                ", languages=" + Arrays.toString(languages) +
                ", countries=" + Arrays.toString(countries) +
                ", summary='" + summary + '\'' +
                ", imdbScore=" + imdbScore +
                ", duration=" + duration +
                ", movie=" + movie +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RemoteMovieInfo that = (RemoteMovieInfo) o;

        if (Double.compare(that.imdbScore, imdbScore) != 0) return false;
        if (duration != that.duration) return false;
        if (posterURL != null ? !posterURL.equals(that.posterURL) : that.posterURL != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(tags, that.tags)) return false;
        if (releaseTime != null ? !releaseTime.equals(that.releaseTime) : that.releaseTime != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(directors, that.directors)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(writers, that.writers)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(actors, that.actors)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(languages, that.languages)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(countries, that.countries)) return false;
        return summary != null ? summary.equals(that.summary) : that.summary == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = posterURL != null ? posterURL.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(tags);
        result = 31 * result + (releaseTime != null ? releaseTime.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(directors);
        result = 31 * result + Arrays.hashCode(writers);
        result = 31 * result + Arrays.hashCode(actors);
        result = 31 * result + Arrays.hashCode(languages);
        result = 31 * result + Arrays.hashCode(countries);
        result = 31 * result + (summary != null ? summary.hashCode() : 0);
        temp = Double.doubleToLongBits(imdbScore);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + duration;
        return result;
    }
}
