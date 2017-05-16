package me.newsong.dao;

import com.github.pagehelper.Page;
import me.newsong.domain.entity.MovieTagDO;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RemoteMovieInfoDOMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated
     */
    int insert(RemoteMovieInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated
     */
    int insertSelective(RemoteMovieInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated
     */
    RemoteMovieInfoDO selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(RemoteMovieInfoDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table movie
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(RemoteMovieInfoDO record);


    Page<RemoteMovieInfoDO> findByNameContaining(@Param("name") String name, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    List<String> findAllMovieNames();

    Page<RemoteMovieInfoDO> findLatestMovies(@Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Page<RemoteMovieInfoDO> findByTagOrderByScore(@Param("tag") String tag, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Page<RemoteMovieInfoDO> findByTagOrderByTime(@Param("tag") String tag, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Integer countByTag(@Param("tag") MovieTagDO tag);

    Integer count();

    RemoteMovieInfoDO findByName(@Param("name") String name);

    Page<RemoteMovieInfoDO> findByMovieIdContaining(@Param("movieId") String movieId, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Page<RemoteMovieInfoDO> findByCountryContaining(@Param("country") String country, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Page<RemoteMovieInfoDO> findByDirectorContaining(@Param("director") String director, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Page<RemoteMovieInfoDO> findByLanguageContaining(@Param("language") String language, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);

    Page<RemoteMovieInfoDO> findByWriterContaining(@Param("writer") String writer, @Param("pageNum") int pageNum, @Param("pageSize") int pageSize);
    
    RemoteMovieInfoDO findByMovieId(@Param("id") String id);
    List<RemoteMovieInfoDO> findAllDisplayMovies();
    List<RemoteMovieInfoDO> findSpecialMovies();
    
    List<String> findAllDisplayMovieIds();
    
    
}