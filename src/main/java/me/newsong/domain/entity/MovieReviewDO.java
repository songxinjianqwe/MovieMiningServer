package me.newsong.domain.entity;

import java.time.LocalDateTime;

public class MovieReviewDO {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.movie_id
     *
     * @mbggenerated
     */
    private String movieId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.user_id
     *
     * @mbggenerated
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.helpful_times
     *
     * @mbggenerated
     */
    private Integer helpfulTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.view_times
     *
     * @mbggenerated
     */
    private Integer viewTimes;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.score
     *
     * @mbggenerated
     */
    private Integer score;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.time
     *
     * @mbggenerated
     */
    private LocalDateTime time;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_review.summary
     *
     * @mbggenerated
     */
    private String summary;
    private Boolean isDisplay;

    private Long movieRecommendId;
    private Long userRecommendId;
    private Double recommendScore;
    
    
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_review
     *
     * @mbggenerated
     */
    public MovieReviewDO(Long id, String movieId, String userId,  Integer helpfulTimes, Integer viewTimes, Integer score, LocalDateTime time, String content, String summary, Boolean isDisplay, Long userRecommendId, Long movieRecommendId,Double recommendScore) {
        this.id = id;
        this.movieId = movieId;
        this.userId = userId;
        this.helpfulTimes = helpfulTimes;
        this.viewTimes = viewTimes;
        this.score = score;
        this.time = time;
        this.content = content;
        this.summary = summary;
        this.isDisplay = isDisplay;
        this.userRecommendId = userRecommendId;
        this.movieRecommendId = movieRecommendId;
        this.recommendScore = recommendScore;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_review
     *
     * @mbggenerated
     */
    public MovieReviewDO() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.id
     *
     * @return the value of movie_review.id
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.id
     *
     * @param id the value for movie_review.id
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.movie_id
     *
     * @return the value of movie_review.movie_id
     * @mbggenerated
     */
    public String getMovieId() {
        return movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.movie_id
     *
     * @param movieId the value for movie_review.movie_id
     * @mbggenerated
     */
    public void setMovieId(String movieId) {
        this.movieId = movieId == null ? null : movieId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.user_id
     *
     * @return the value of movie_review.user_id
     * @mbggenerated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.user_id
     *
     * @param userId the value for movie_review.user_id
     * @mbggenerated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }


    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.helpful_times
     *
     * @return the value of movie_review.helpful_times
     * @mbggenerated
     */
    public Integer getHelpfulTimes() {
        return helpfulTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.helpful_times
     *
     * @param helpfulTimes the value for movie_review.helpful_times
     * @mbggenerated
     */
    public void setHelpfulTimes(Integer helpfulTimes) {
        this.helpfulTimes = helpfulTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.view_times
     *
     * @return the value of movie_review.view_times
     * @mbggenerated
     */
    public Integer getViewTimes() {
        return viewTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.view_times
     *
     * @param viewTimes the value for movie_review.view_times
     * @mbggenerated
     */
    public void setViewTimes(Integer viewTimes) {
        this.viewTimes = viewTimes;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.score
     *
     * @return the value of movie_review.score
     * @mbggenerated
     */
    public Integer getScore() {
        return score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.score
     *
     * @param score the value for movie_review.score
     * @mbggenerated
     */
    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.time
     *
     * @return the value of movie_review.time
     * @mbggenerated
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.time
     *
     * @param time the value for movie_review.time
     * @mbggenerated
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.content
     *
     * @return the value of movie_review.content
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.content
     *
     * @param content the value for movie_review.content
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_review.summary
     *
     * @return the value of movie_review.summary
     * @mbggenerated
     */
    public String getSummary() {
        return summary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_review.summary
     *
     * @param summary the value for movie_review.summary
     * @mbggenerated
     */
    public void setSummary(String summary) {
        this.summary = summary == null ? null : summary.trim();
    }

    public Boolean getDisplay() {
        return isDisplay;
    }

    public void setDisplay(Boolean display) {
        isDisplay = display;
    }

    public Long getMovieRecommendId() {
        return movieRecommendId;
    }

    public void setMovieRecommendId(Long movieRecommendId) {
        this.movieRecommendId = movieRecommendId;
    }

    public Long getUserRecommendId() {
        return userRecommendId;
    }

    public void setUserRecommendId(Long userRecommendId) {
        this.userRecommendId = userRecommendId;
    }

    public Double getRecommendScore() {
        return recommendScore;
    }

    public void setRecommendScore(Double recommendScore) {
        this.recommendScore = recommendScore;
    }

    @Override
    public String toString() {
        return "MovieReviewDO{" +
                "id=" + id +
                ", movieId='" + movieId + '\'' +
                ", userId='" + userId + '\'' +
                ", helpfulTimes=" + helpfulTimes +
                ", viewTimes=" + viewTimes +
                ", score=" + score +
                ", time=" + time +
                ", content='" + content + '\'' +
                ", summary='" + summary + '\'' +
                ", isDisplay=" + isDisplay +
                ", movieRecommendId=" + movieRecommendId +
                ", userRecommendId=" + userRecommendId +
                ", recommendScore=" + recommendScore +
                '}';
    }
}