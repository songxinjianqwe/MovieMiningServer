package me.newsong.dao;

import me.newsong.domain.entity.MovieCountryKey;
import org.apache.ibatis.annotations.Param;

public interface MovieCountryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_country
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(MovieCountryKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_country
     *
     * @mbggenerated
     */
    int insert(MovieCountryKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_country
     *
     * @mbggenerated
     */
    int insertSelective(MovieCountryKey record);
    MovieCountryKey findById(@Param("movieId") Long movieId, @Param("countryId") Long countryId);
}