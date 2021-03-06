package me.newsong.domain.entity;

public class MovieActorKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_actor.movie_id
     *
     * @mbggenerated
     */
    private Long movieId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_actor.actor_id
     *
     * @mbggenerated
     */
    private Long actorId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_actor
     *
     * @mbggenerated
     */
    public MovieActorKey(Long movieId, Long actorId) {
        this.movieId = movieId;
        this.actorId = actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_actor
     *
     * @mbggenerated
     */
    public MovieActorKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_actor.movie_id
     *
     * @return the value of movie_actor.movie_id
     *
     * @mbggenerated
     */
    public Long getMovieId() {
        return movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_actor.movie_id
     *
     * @param movieId the value for movie_actor.movie_id
     *
     * @mbggenerated
     */
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_actor.actor_id
     *
     * @return the value of movie_actor.actor_id
     *
     * @mbggenerated
     */
    public Long getActorId() {
        return actorId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_actor.actor_id
     *
     * @param actorId the value for movie_actor.actor_id
     *
     * @mbggenerated
     */
    public void setActorId(Long actorId) {
        this.actorId = actorId;
    }
}