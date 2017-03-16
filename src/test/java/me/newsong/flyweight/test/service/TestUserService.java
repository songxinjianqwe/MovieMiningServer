package me.newsong.flyweight.test.service;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.DescLengthRange;
import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.domain.User;
import me.newsong.flyweight.service.iface.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class TestUserService {
	@Autowired
	private UserService service;
	@Autowired
	@Qualifier("Cached")
	private MovieReviewRepository dao;

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
		List<MovieReview> reviews = dao.findByUserId("A2S4S7JW1JSFXU");
		reviews.forEach(System.out::println);
	}

	@Test
	public void testGetUserKeyWords() {
		// TODO
	}

	@Test
	public void testFindDescLengthsWithRange() {
		User user = service.findUserById("A2S4S7JW1JSFXU");
		List<MovieReview> reviews = dao.findByUserId("A2S4S7JW1JSFXU");
		Map<DescLengthRange, Long> map = service.findDescLengthsWithRange("A2S4S7JW1JSFXU", 10);
		for (Entry<DescLengthRange, Long> entry : map.entrySet()) {
			System.out.println(entry);
		}
	}

}
