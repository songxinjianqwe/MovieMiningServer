package me.newsong.flyweight.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import me.newsong.flyweight.enums.TimeUnit;
import me.newsong.flyweight.utils.BaseSpringTester;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.time.Month;
import me.newsong.flyweight.domain.entity.Movie;
import me.newsong.flyweight.domain.entity.MovieReview;
import me.newsong.flyweight.domain.time.Season;
import me.newsong.flyweight.service.iface.MovieService;
import me.newsong.flyweight.service.impl.comp.MovieReviewTimeDescComparator;


public class TestMovieService extends BaseSpringTester {
	@Autowired
	private MovieService service;
	@Autowired
	@Qualifier("CachedReviews")
	private MovieReviewRepository dao;

	@Test
	public void testFindAllMovieIds() {
		List<String> movieIds = service.findAllIds();
		assertNotNull(movieIds);
		assertTrue(movieIds.size() > 0);
		// movieIds.forEach(System.out::println);
	}

	@Test
	public void testFindMovieByID() {
		Movie movie = service.findMovieByID("B0002RGNRU");
		assertEquals(movie.getId(), "B0002RGNRU");
		System.out.println(movie);
	}

	@Test
	public void testGetVarianceOfScore() {
		Movie movie = service.findMovieByID("B0002RGNRU");
		List<MovieReview> reviews = dao.findByMovieId("B0002RGNRU");
		List<Integer> raw = reviews.stream().map(MovieReview::getScore).collect(Collectors.toList());
		double[] data = new double[raw.size()];
		for (int i = 0; i < data.length; ++i) {
			data[i] = raw.get(i);
		}
		// System.out.println(movie.getVarianceOfScore());
		// System.out.println(getVariance(data));
		assertEquals(getVariance(data), movie.getVarianceOfScore(), 0.01);
	}

	private static double getVariance(double[] inputData) {
		int count = getCount(inputData);
		double sqrsum = getSquareSum(inputData);
		double average = getAverage(inputData);
		double result;
		result = (sqrsum - count * average * average) / count;

		return result;
	}

	private static int getCount(double[] inputData) {
		if (inputData == null)
			return -1;

		return inputData.length;
	}

	private static double getAverage(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double result;
		result = getSum(inputData) / len;
		System.out.println("Test: avg" + result);
		return result;
	}

	private static double getSum(double[] inputData) {
		if (inputData == null || inputData.length == 0)
			return -1;
		int len = inputData.length;
		double sum = 0;
		for (int i = 0; i < len; i++) {
			sum = sum + inputData[i];
		}

		return sum;

	}

	private static double getSquareSum(double[] inputData) {
		if (inputData == null || inputData.length == 0) {
			return -1;
		}
		int len = inputData.length;
		double sqrsum = 0.0;
		for (int i = 0; i < len; i++) {
			sqrsum = sqrsum + inputData[i] * inputData[i];
		}
		System.out.println("Test SquareSum:" + sqrsum);
		return sqrsum;
	}

	@Test
	public void testGetMovieKeyWords() {

	}

	@Test
	public void testFindScoresByStar() {
		Map<Integer, Long> map = service.findScoresByStar("B0002RGNRU");
		List<MovieReview> list = dao.findByMovieId("B0002RGNRU");
		System.out.println(map);
		int[] counts = new int[5];
		for (int i = 0; i < counts.length; ++i) {
			for (int j = 0; j < list.size(); ++j) {
				if (list.get(j).getScore() == i + 1) {
					counts[i]++;
				}
			}
		}
		for (int i = 0; i < counts.length; ++i) {
			assertEquals(counts[i], map.get(i + 1).intValue());
		}
	}

	@Test
	public void testEarliestAndLastestTime() {
		Movie movie = service.findMovieByID("B0002RGNRU");
		System.out.println(movie.getEarliestReviewTime().getTime());
		System.out.println(movie.getLatestReviewTime().getTime());
		List<MovieReview> reviews = dao.findByMovieId("B0002RGNRU");
		assertEquals(movie.getEarliestReviewTime(),
				reviews.stream().collect(Collectors.minBy(new MovieReviewTimeDescComparator())).get().getTime());
		assertEquals(movie.getLatestReviewTime(),
				reviews.stream().collect(Collectors.maxBy(new MovieReviewTimeDescComparator())).get().getTime());
	}

	@Test
	public void testFindAccumulatedReviewCountsByMonth() {
		Movie movie = service.findMovieByID("B0002RGNRU");
		System.out.println("reviewTimes:" + movie.getReviewTimes());
		Map<Month, Long> map = service.findAccumulatedReviewCountsBy(TimeUnit.Month, "B0002RGNRU",
				movie.getEarliestReviewTime(), movie.getLatestReviewTime());
		System.out.println("Result");
		for (Entry<Month, Long> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "    " + entry.getValue());
		}
		assertEquals(movie.getReviewTimes(), map.get(new Month(2012, 10)).intValue());
	}
	
	@Test
	public void testFindAccumulatedReviewCountsBySeason() {
		Movie movie = service.findMovieByID("B0002RGNRU");
		System.out.println("reviewTimes:" + movie.getReviewTimes());
		Map<Season, Long> map = service.findAccumulatedReviewCountsBy(TimeUnit.Season, "B0002RGNRU",
				movie.getEarliestReviewTime(), movie.getLatestReviewTime());
		System.out.println("Result");
		for (Entry<Season, Long> entry : map.entrySet()) {
			System.out.println(entry.getKey() + "    " + entry.getValue());
		}
		assertEquals(movie.getReviewTimes(), map.get(new Season(2012, 3)).intValue());
	}

}
