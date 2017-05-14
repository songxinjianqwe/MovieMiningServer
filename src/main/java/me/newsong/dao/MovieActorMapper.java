package me.newsong.dao;

import me.newsong.domain.entity.MovieActorKey;
import org.apache.ibatis.annotations.Param;

public interface MovieActorMapper  {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_actor
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(MovieActorKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_actor
     *
     * @mbggenerated
     */
    int insert(MovieActorKey record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie_actor
     *
     * @mbggenerated
     */
    int insertSelective(MovieActorKey record);
    MovieActorKey findById(@Param("movieId") Long movieId,@Param("actorId") Long actorId);
}