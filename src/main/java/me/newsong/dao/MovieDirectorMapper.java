package me.newsong.dao;

import me.newsong.domain.entity.MovieDirectorKey;
import org.apache.ibatis.annotations.Param;

public interface MovieDirectorMapper  {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_director
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(MovieDirectorKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_director
     *
     * @mbggenerated
     */
    int insert(MovieDirectorKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_director
     *
     * @mbggenerated
     */
    int insertSelective(MovieDirectorKey record);
    MovieDirectorKey findById(@Param("movieId") Long movieId, @Param("directorId") Long directorId);
}