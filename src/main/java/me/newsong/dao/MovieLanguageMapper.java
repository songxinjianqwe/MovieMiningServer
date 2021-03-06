package me.newsong.dao;

import me.newsong.domain.entity.MovieLanguageKey;
import org.apache.ibatis.annotations.Param;

public interface MovieLanguageMapper  {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_language
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(MovieLanguageKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_language
     *
     * @mbggenerated
     */
    int insert(MovieLanguageKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_language
     *
     * @mbggenerated
     */
    int insertSelective(MovieLanguageKey record);
    MovieLanguageKey findById(@Param("movieId") Long movieId, @Param("languageId") Long languageId);

}