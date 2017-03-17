package me.newsong.flyweight.domain;

import me.newsong.flyweight.enums.MovieTag;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Movie {
	private String id;
	private Date earliestReviewTime;
	private Date latestReviewTime;
	private int reviewTimes;
	private double varianceOfScore;
	private List<String> keyWords;
	private double avgScore;
	/**
	 * 字节数组使用Base64转为字符串
	 */
	private String poster;
	private String name;
	private MovieTag[] tags;
	private Date releaseTime;
	private String director;
	private String[] writers;
	private String[] actors;
	private String[] languages;
	private String[] countries;
	private String summery;
	private double IMDBScore;
	private int duration;
	
	
	public Movie() {
	}

	public Movie(String id, Date earliestReviewTime, Date latestReviewTime, int reviewTimes, double varianceOfScore,
				 List<String> keyWords, double avgScore) {
		super();
		this.id = id;
		this.earliestReviewTime = earliestReviewTime;
		this.latestReviewTime = latestReviewTime;
		this.reviewTimes = reviewTimes;
		this.varianceOfScore = varianceOfScore;
		this.keyWords = keyWords;
		this.avgScore = avgScore;
	}

	public Movie(String id, Date earliestReviewTime, Date latestReviewTime, int reviewTimes, double varianceOfScore, List<String> keyWords, double avgScore, String poster, String name, MovieTag[] tags, Date releaseTime, String director, String[] writers, String[] actors, String[] languages, String[] countries, String summery, double IMDBScore, int duration) {
		this.id = id;
		this.earliestReviewTime = earliestReviewTime;
		this.latestReviewTime = latestReviewTime;
		this.reviewTimes = reviewTimes;
		this.varianceOfScore = varianceOfScore;
		this.keyWords = keyWords;
		this.avgScore = avgScore;
		this.poster = poster;
		this.name = name;
		this.tags = tags;
		this.releaseTime = releaseTime;
		this.director = director;
		this.writers = writers;
		this.actors = actors;
		this.languages = languages;
		this.countries = countries;
		this.summery = summery;
		this.IMDBScore = IMDBScore;
		this.duration = duration;
	}

	public String getId() {
		return id;
	}

	public Date getEarliestReviewTime() {
		return earliestReviewTime;
	}

	public Date getLatestReviewTime() {
		return latestReviewTime;
	}

	public int getReviewTimes() {
		return reviewTimes;
	}

	public double getVarianceOfScore() {
		return varianceOfScore;
	}

	public List<String> getKeyWords() {
		return keyWords;
	}

	public double getAvgScore() {
		return avgScore;
	}

	public String getPoster() {
		return poster;
	}

	public String getName() {
		return name;
	}

	public MovieTag[] getTags() {
		return tags;
	}

	public Date getReleaseTime() {
		return releaseTime;
	}

	public String getDirector() {
		return director;
	}

	public String[] getWriters() {
		return writers;
	}

	public String[] getActors() {
		return actors;
	}

	public String[] getLanguages() {
		return languages;
	}

	public String[] getCountries() {
		return countries;
	}

	public String getSummery() {
		return summery;
	}

	public double getIMDBScore() {
		return IMDBScore;
	}

	public int getDuration() {
		return duration;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEarliestReviewTime(Date earliestReviewTime) {
		this.earliestReviewTime = earliestReviewTime;
	}

	public void setLatestReviewTime(Date latestReviewTime) {
		this.latestReviewTime = latestReviewTime;
	}

	public void setReviewTimes(int reviewTimes) {
		this.reviewTimes = reviewTimes;
	}

	public void setVarianceOfScore(double varianceOfScore) {
		this.varianceOfScore = varianceOfScore;
	}

	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}

	public void setAvgScore(double avgScore) {
		this.avgScore = avgScore;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTags(MovieTag[] tags) {
		this.tags = tags;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public void setWriters(String[] writers) {
		this.writers = writers;
	}

	public void setActors(String[] actors) {
		this.actors = actors;
	}

	public void setLanguages(String[] languages) {
		this.languages = languages;
	}

	public void setCountries(String[] countries) {
		this.countries = countries;
	}

	public void setSummery(String summery) {
		this.summery = summery;
	}

	public void setIMDBScore(double IMDBScore) {
		this.IMDBScore = IMDBScore;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Movie movie = (Movie) o;

		if (reviewTimes != movie.reviewTimes) return false;
		if (Double.compare(movie.varianceOfScore, varianceOfScore) != 0) return false;
		if (Double.compare(movie.avgScore, avgScore) != 0) return false;
		if (Double.compare(movie.IMDBScore, IMDBScore) != 0) return false;
		if (duration != movie.duration) return false;
		if (id != null ? !id.equals(movie.id) : movie.id != null) return false;
		if (earliestReviewTime != null ? !earliestReviewTime.equals(movie.earliestReviewTime) : movie.earliestReviewTime != null)
			return false;
		if (latestReviewTime != null ? !latestReviewTime.equals(movie.latestReviewTime) : movie.latestReviewTime != null)
			return false;
		if (keyWords != null ? !keyWords.equals(movie.keyWords) : movie.keyWords != null) return false;
		if (poster != null ? !poster.equals(movie.poster) : movie.poster != null) return false;
		if (name != null ? !name.equals(movie.name) : movie.name != null) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(tags, movie.tags)) return false;
		if (releaseTime != null ? !releaseTime.equals(movie.releaseTime) : movie.releaseTime != null) return false;
		if (director != null ? !director.equals(movie.director) : movie.director != null) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(writers, movie.writers)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(actors, movie.actors)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(languages, movie.languages)) return false;
		// Probably incorrect - comparing Object[] arrays with Arrays.equals
		if (!Arrays.equals(countries, movie.countries)) return false;
		return summery != null ? summery.equals(movie.summery) : movie.summery == null;
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
		result = 31 * result + (poster != null ? poster.hashCode() : 0);
		result = 31 * result + (name != null ? name.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(tags);
		result = 31 * result + (releaseTime != null ? releaseTime.hashCode() : 0);
		result = 31 * result + (director != null ? director.hashCode() : 0);
		result = 31 * result + Arrays.hashCode(writers);
		result = 31 * result + Arrays.hashCode(actors);
		result = 31 * result + Arrays.hashCode(languages);
		result = 31 * result + Arrays.hashCode(countries);
		result = 31 * result + (summery != null ? summery.hashCode() : 0);
		temp = Double.doubleToLongBits(IMDBScore);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		result = 31 * result + duration;
		return result;
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
				", poster='" + poster + '\'' +
				", name='" + name + '\'' +
				", tags=" + Arrays.toString(tags) +
				", releaseTime=" + releaseTime +
				", director='" + director + '\'' +
				", writers=" + Arrays.toString(writers) +
				", actors=" + Arrays.toString(actors) +
				", languages=" + Arrays.toString(languages) +
				", countries=" + Arrays.toString(countries) +
				", summery='" + summery + '\'' +
				", IMDBScore=" + IMDBScore +
				", duration=" + duration +
				'}';
	}
}
