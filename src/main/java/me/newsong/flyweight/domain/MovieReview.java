package me.newsong.flyweight.domain;

import java.util.Date;

public class MovieReview {
	private String movieId;
	private String userId;
	private String username;
	private int helpfulTimes;
	private int viewTimes;
	private int score;
	private Date time;
	private String summary;
	private String content;

	public MovieReview() {
	}

	public MovieReview(String movieId, String userId, String username, int helpfulTimes, int viewTimes, int score,
			Date time, String summary, String content) {
		super();
		this.movieId = movieId;
		this.userId = userId;
		this.username = username;
		this.helpfulTimes = helpfulTimes;
		this.viewTimes = viewTimes;
		this.score = score;
		this.time = time;
		this.summary = summary;
		this.content = content;
	}

	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getHelpfulTimes() {
		return helpfulTimes;
	}

	public void setHelpfulTimes(int helpfulTimes) {
		this.helpfulTimes = helpfulTimes;
	}

	public int getViewTimes() {
		return viewTimes;
	}

	public void setViewTimes(int viewTimes) {
		this.viewTimes = viewTimes;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "MovieReview [movieId=" + movieId + ", userId=" + userId + ", username=" + username + ", helpfulTimes="
				+ helpfulTimes + ", viewTimes=" + viewTimes + ", score=" + score + ", time=" + time + ", summary="
				+ summary + ", content=" + content + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + helpfulTimes;
		result = prime * result + ((movieId == null) ? 0 : movieId.hashCode());
		result = prime * result + score;
		result = prime * result + ((summary == null) ? 0 : summary.hashCode());
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + viewTimes;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieReview other = (MovieReview) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (helpfulTimes != other.helpfulTimes)
			return false;
		if (movieId == null) {
			if (other.movieId != null)
				return false;
		} else if (!movieId.equals(other.movieId))
			return false;
		if (score != other.score)
			return false;
		if (summary == null) {
			if (other.summary != null)
				return false;
		} else if (!summary.equals(other.summary))
			return false;
		if (time == null) {
			if (other.time != null)
				return false;
		} else if (!time.equals(other.time))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (viewTimes != other.viewTimes)
			return false;
		return true;
	}

	
}
