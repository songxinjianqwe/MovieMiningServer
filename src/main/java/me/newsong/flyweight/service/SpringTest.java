package me.newsong.flyweight.service;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.service.iface.MovieService;

public class SpringTest {
	private ApplicationContext ctx;
	private MovieService service;
	private ObjectMapper mapper;
	@Before
	public void setUp() throws Exception {
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		service = ctx.getBean(MovieService.class);
		mapper = new ObjectMapper();
	}

	@Test
	public void test() {
//		PythonUtil util = PythonUtil.getInstance();
//		List<Movie> list = util.callForList("sortByScore", service.findAll(),Movie.class);
//		list.forEach(System.out::println);
		
	}
	
	@Test
	public void test2(){
//		List<MovieReview> list = service.findMovieReviewsByID("B001HN68ZU");
//		for(MovieReview review:list){
//			System.out.println(review);
//		}
		
	}
	
}
