
package me.newsong.flyweight.dao.impl.movie;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.newsong.flyweight.dao.iface.movie.MovieRepository;
import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.dao.impl.movie_review.MovieReviewRepositoryCachedImpl;
import me.newsong.flyweight.domain.Index;
import me.newsong.flyweight.domain.RemoteMovieInfo;
import me.newsong.flyweight.utils.PythonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


/**
 * Created by SinjinSong on 2017/3/16.
 */

@Repository("CachedMovies")
public class MovieRepositoryCachedImpl implements MovieRepository {
    private Map<String, RemoteMovieInfo> moviesById;
    private ResourceBundle rb;
    private ObjectMapper mapper;
    
    public MovieRepositoryCachedImpl() {
        rb = ResourceBundle.getBundle("fileSystemData");
        mapper = new ObjectMapper();
//        try {
//            moviesById = mapper.readValue(MovieRepositoryCachedImpl.class.getClassLoader().getResourceAsStream(rb.getString("remoteMovieDir")),
//                    new TypeReference<Map<String, RemoteMovieInfo>>(){});
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public RemoteMovieInfo findMovieViaCrawler(String id) {
        return moviesById.get(id);
    }
}

