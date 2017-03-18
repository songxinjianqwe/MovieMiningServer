package me.newsong.flyweight.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.newsong.flyweight.dao.iface.movie.MovieRepository;
import me.newsong.flyweight.domain.Movie;
import me.newsong.flyweight.domain.RemoteMovieInfo;
import me.newsong.flyweight.service.iface.MovieService;
import me.newsong.flyweight.utils.PythonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class SpringTest {
    @Autowired
    private MovieRepository dao;
    @Autowired
    private MovieService service;
    private ObjectMapper mapper;

    @Before
    public void setUp() throws Exception {
        mapper = new ObjectMapper();
    }

    @Test
    public void test() {
//		PythonUtil util = PythonUtil.getInstance();
//		List<Movie> list = util.callForList("sortByScore", service.findAll(),Movie.class);
//		list.forEach(System.out::println);

    }

    @Test
    public void test2() {
//		List<MovieReview> list = service.findMovieReviewsByID("B001HN68ZU");
//		for(MovieReview review:list){
//			System.out.println(review);
//		}

    }

    @Test
    public void test3() {
        RemoteMovieInfo movie = dao.findMovieViaCrawler("B000BZISP8");
        System.out.println(movie);
    }

    @Test
    public void test4() throws IOException {
        String jsonObj = "{\"name\": \"Constantine\", \"imdbScore\": \"6.9\", \"summary\": \"Supernatural detective John Constantine helps a policewoman prove her sister's death was not a suicide, but something more.\", \"directors\": [\"Francis Lawrence\"], \"writers\": [\"Jamie Delano\", \"Garth Ennis\"], \"actors\": [\"Keanu Reeves\", \"Rachel Weisz\", \"Djimon Hounsou\"], \"tags\": [\"Drama\", \"Fantasy\", \"Horror\", \"Thriller\"], \"countries\": [\"USA\", \"Germany\"], \"languages\": [\"English\", \"Filipino\", \"Tagalog\"], \"releaseTime\": \"2005-2-17\", \"duration\": \"121\", \"posterURL\": \"https://images-na.ssl-images-amazon.com/images/M/MV5BMTE5NDk5NTUyN15BMl5BanBnXkFtZTYwNzUyMDA3._V1_UX182_CR0,0,182,268_AL_.jpg\"}";
        RemoteMovieInfo info = mapper.readValue(jsonObj.getBytes(), RemoteMovieInfo.class);
        System.out.println(info);
    }
    
    
}
