package me.newsong.domain.entity;

public class MovieCountryKey {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_country.movie_id
     *
     * @mbggenerated
     */
    private Long movieId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column movie_country.country_id
     *
     * @mbggenerated
     */
    private Long countryId;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_country
     *
     * @mbggenerated
     */
    public MovieCountryKey(Long movieId, Long countryId) {
        this.movieId = movieId;
        this.countryId = countryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_country
     *
     * @mbggenerated
     */
    public MovieCountryKey() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_country.movie_id
     *
     * @return the value of movie_country.movie_id
     *
     * @mbggenerated
     */
    public Long getMovieId() {
        return movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_country.movie_id
     *
     * @param movieId the value for movie_country.movie_id
     *
     * @mbggenerated
     */
    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column movie_country.country_id
     *
     * @return the value of movie_country.country_id
     *
     * @mbggenerated
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column movie_country.country_id
     *
     * @param countryId the value for movie_country.country_id
     *
     * @mbggenerated
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }
}