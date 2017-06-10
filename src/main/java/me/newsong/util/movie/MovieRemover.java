package me.newsong.util.movie;

import com.csvreader.CsvReader;
import me.newsong.dao.MovieReviewDOMapper;
import me.newsong.dao.RemoteMovieInfoDOMapper;
import me.newsong.domain.entity.RemoteMovieInfoDO;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SinjinSong on 2017/6/10.
 */
public class MovieRemover extends BaseSpringTester {
    @Autowired
    private MovieReviewDOMapper movieReviewDOMapper;
    @Autowired
    private RemoteMovieInfoDOMapper remoteMovieInfoDOMapper;
    
    @Test
    public void remove() throws IOException {
        CsvReader csvReader;
        csvReader = new CsvReader("E:/movies.csv", ',', Charset.forName("UTF-8"));
        //读取表头
        csvReader.readHeaders();
        //逐条读取记录，直至读完
        List<Long> allMovieRecommendIds = remoteMovieInfoDOMapper.findAllMovieRecommendIds();
        List<Long> valid = new ArrayList<>();
        while (csvReader.readRecord()) {
            Long movieRecommendId = Long.valueOf(csvReader.get("movieId"));
            valid.add(movieRecommendId);
        }
        allMovieRecommendIds.removeAll(valid);
        System.out.println(allMovieRecommendIds.size());
        for (Long allMovieRecommendId : allMovieRecommendIds) {
//            movieReviewDOMapper.deleteByMovieRecommendId(allMovieRecommendId);
            RemoteMovieInfoDO movie = remoteMovieInfoDOMapper.findByRecommendId(allMovieRecommendId);
            remoteMovieInfoDOMapper.deleteByPrimaryKey(movie.getId());
        }
    }
}
