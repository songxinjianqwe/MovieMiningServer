package me.newsong.flyweight.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.newsong.flyweight.domain.Movie;
import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.exceptions.MovieNotFoundException;
import me.newsong.flyweight.service.iface.MovieService;

@Transactional(readOnly = true)
@Service
public class MovieServiceImpl extends BaseMovieReviewHandler implements MovieService {
	@Override
	public List<String> findAllIds() {
		return dao.findAllMovieIds();
	}

	@Override
	public Movie findMovieByID(String id) {
		List<MovieReview> reviews = findMovieReviewsSortedByTime(id);
		Movie movie = new Movie(id, reviews.get(0).getTime(), reviews.get(reviews.size() - 1).getTime(), reviews.size(),
				getVarianceOfScore(reviews), dao.getMovieKeyWords(id), getAverageScore(reviews));
		return movie;
	}

	/**
	 * 只在一个地方抛出异常
	 * 
	 * @param id
	 * @return
	 */
	protected List<MovieReview> findMovieReviewById(String id) {
		List<MovieReview> reviews = dao.findByMovieId(id);
		if (reviews.size() == 0) {
			throw new MovieNotFoundException(id, null);
		}
		return reviews;
	}

	/**
	 * 平方的平均-平均的平方
	 */
	@Override
	public double getVarianceOfScore(List<MovieReview> reviews) {
		long squareSum = reviews.stream()
				.collect(Collectors.summarizingInt((review) -> review.getScore() * review.getScore())).getSum();
		double avg = reviews.stream().collect(Collectors.averagingDouble(MovieReview::getScore));
		// System.out.println("SquareSum "+squareSum);
		// System.out.println("avg "+avg);
		return 1.0 * squareSum / reviews.size() - avg * avg;
	}

	@Override
	public Map<Integer, Long> findScoresByStar(String id) {
		return findMovieReviewById(id).stream()
				.collect(Collectors.groupingBy(MovieReview::getScore, Collectors.counting()));
	}

	@Override
	public double getAverageScore(List<MovieReview> reviews) {
		return reviews.stream().collect(Collectors.averagingDouble(MovieReview::getScore));
	}
}
