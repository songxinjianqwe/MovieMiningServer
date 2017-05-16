package me.newsong.dao;

import me.newsong.domain.entity.MovieTagKey;
import org.apache.ibatis.annotations.Param;

public interface MovieTagMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_tag
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(MovieTagKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_tag
     *
     * @mbggenerated
     */
    int insert(MovieTagKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_tag
     *
     * @mbggenerated
     */
    int insertSelective(MovieTagKey record);
    MovieTagKey findById(@Param("movieId") Long movieId, @Param("tagId") Long tagId);
}