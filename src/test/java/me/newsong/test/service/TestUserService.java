package me.newsong.test.service;

import me.newsong.dao.MovieReviewDOMapper;
import me.newsong.domain.common.DescLengthRange;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.entity.User;
import me.newsong.domain.time.BaseTimeUnit;
import me.newsong.enums.TimeUnit;
import me.newsong.service.UserService;
import me.newsong.util.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class TestUserService extends BaseSpringTester {
	@Autowired
	private UserService service;
    @Autowired
    private MovieReviewDOMapper dao;
    
	@Test
	public void testFindReviews() {
		for (String id : dao.findAllMovieIds()) {
			System.out.println(service.findUserById(id));
		}
	}

	@Test
	public void testFindAllUserIds() {
		List<String> ids = service.findAllIds();
		assertNotNull(ids);
		assertTrue(ids.size() > 0);
		System.out.println(ids);
	}

	@Test
	public void testFindById() {
		User user = service.findUserById("A2S4S7JW1JSFXU");
		System.out.println(user);
		assertEquals(user.getId(), "A2S4S7JW1JSFXU");
		List<MovieReviewDO> reviews = dao.findByUserId("A2S4S7JW1JSFXU");
		reviews.forEach(System.out::println);
	}

	@Test
	public void testFindDescLengthsWithRange() {
		Map<DescLengthRange, Long> map = service.findDescLengthsWithRange("ur0562732", 10);
	    for(Map.Entry<DescLengthRange,Long> entry:map.entrySet()){
            System.out.println(entry);
        }
	}
	
	@Test
	public void testFindAccumulatedReviewCountsBySeason() {
        User user = service.findUserById("ur3922673");
        System.out.println(user);
		Map<BaseTimeUnit, Long> map = service.findAccumulatedReviewCountsBy(TimeUnit.Season, "ur3922673",
                LocalDateTime.of(2005,1,1,0,0,0),LocalDateTime.of(2010,1,1,0,0,0));
		System.out.println("Result");
		for (Map.Entry<BaseTimeUnit, Long> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "    " + entry.getValue());
		}
	}
	
	@Test
	public void test(){
	    service.findPagingMovieReviewsByUserRecommendId(2L,1,5).getList().forEach(System.out::println);
	    service.findPagingMovieReviewsByUserRecommendId(2L,2,5).getList().forEach(System.out::println);
	    
	    
    }
}
