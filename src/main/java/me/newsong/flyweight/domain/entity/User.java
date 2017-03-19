package me.newsong.flyweight.domain.entity;

import java.util.Date;
import java.util.List;

public class User {
	private String id;
	private Date earliestReviweTime;
	private Date lastestReviweTime;
	private int reviewTimes;
	private List<String> keyWords;
	private double avgDescLength;
	
	public User() {
	}

	public User(String id, Date earliestReviweTime, Date lastestReviweTime, int reviewTimes, List<String> keyWords,
			double avgDescLength) {
		super();
		this.id = id;
		this.earliestReviweTime = earliestReviweTime;
		this.lastestReviweTime = lastestReviweTime;
		this.reviewTimes = reviewTimes;
		this.keyWords = keyWords;
		this.avgDescLength = avgDescLength;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getEarliestReviweTime() {
		return earliestReviweTime;
	}

	public void setEarliestReviweTime(Date earliestReviweTime) {
		this.earliestReviweTime = earliestReviweTime;
	}

	public Date getLastestReviweTime() {
		return lastestReviweTime;
	}

	public void setLastestReviweTime(Date lastestReviweTime) {
		this.lastestReviweTime = lastestReviweTime;
	}

	public int getReviewTimes() {
		return reviewTimes;
	}

	public void setReviewTimes(int reviewTimes) {
		this.reviewTimes = reviewTimes;
	}

	public List<String> getKeyWords() {
		return keyWords;
	}

	public void setKeyWords(List<String> keyWords) {
		this.keyWords = keyWords;
	}

	public double getAvgDescLength() {
		return avgDescLength;
	}

	public void setAvgDescLength(double avgDescLength) {
		this.avgDescLength = avgDescLength;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", earliestReviweTime=" + earliestReviweTime + ", lastestReviweTime="
				+ lastestReviweTime + ", reviewTimes=" + reviewTimes + ", keyWords=" + keyWords + ", avgDescLength="
				+ avgDescLength + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(avgDescLength);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((earliestReviweTime == null) ? 0 : earliestReviweTime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((keyWords == null) ? 0 : keyWords.hashCode());
		result = prime * result + ((lastestReviweTime == null) ? 0 : lastestReviweTime.hashCode());
		result = prime * result + reviewTimes;
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
		User other = (User) obj;
		if (Double.doubleToLongBits(avgDescLength) != Double.doubleToLongBits(other.avgDescLength))
			return false;
		if (earliestReviweTime == null) {
			if (other.earliestReviweTime != null)
				return false;
		} else if (!earliestReviweTime.equals(other.earliestReviweTime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (keyWords == null) {
			if (other.keyWords != null)
				return false;
		} else if (!keyWords.equals(other.keyWords))
			return false;
		if (lastestReviweTime == null) {
			if (other.lastestReviweTime != null)
				return false;
		} else if (!lastestReviweTime.equals(other.lastestReviweTime))
			return false;
		if (reviewTimes != other.reviewTimes)
			return false;
		return true;
	}

	
	
}
