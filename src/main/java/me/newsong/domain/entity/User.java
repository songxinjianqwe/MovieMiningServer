package me.newsong.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private String id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime earliestReviewTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime latestReviewTime;
    private int reviewTimes;
    private List<String> keyWords;
    private double avgDescLength;

    public User() {
    }

    public User(String id, LocalDateTime earliestReviewTime, LocalDateTime latestReviewTime, int reviewTimes, List<String> keyWords,
                double avgDescLength) {
        super();
        this.id = id;
        this.earliestReviewTime = earliestReviewTime;
        this.latestReviewTime = latestReviewTime;
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
        return "User [id=" + id + ", earliestReviewTime=" + earliestReviewTime + ", latestReviewTime="
                + latestReviewTime + ", reviewTimes=" + reviewTimes + ", keyWords=" + keyWords + ", avgDescLength="
                + avgDescLength + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(avgDescLength);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((earliestReviewTime == null) ? 0 : earliestReviewTime.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((keyWords == null) ? 0 : keyWords.hashCode());
        result = prime * result + ((latestReviewTime == null) ? 0 : latestReviewTime.hashCode());
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
        if (earliestReviewTime == null) {
            if (other.earliestReviewTime != null)
                return false;
        } else if (!earliestReviewTime.equals(other.earliestReviewTime))
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
        if (latestReviewTime == null) {
            if (other.latestReviewTime != null)
                return false;
        } else if (!latestReviewTime.equals(other.latestReviewTime))
            return false;
        if (reviewTimes != other.reviewTimes)
            return false;
        return true;
    }


}
