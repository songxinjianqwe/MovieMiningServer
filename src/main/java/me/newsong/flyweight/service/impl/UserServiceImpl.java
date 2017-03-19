package me.newsong.flyweight.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.newsong.flyweight.domain.DescLengthRange;
import me.newsong.flyweight.domain.entity.MovieReview;
import me.newsong.flyweight.domain.entity.User;
import me.newsong.flyweight.exceptions.UserNotFoundException;
import me.newsong.flyweight.service.iface.UserService;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl extends MovieReviewTemplateImpl implements UserService {
	
	@Override
	public List<String> findAllIds() {
		return dao.findAllUserIds();
	}

	@Override
	public User findUserById(String id) {
		List<MovieReview> reviews = findMovieReviewsSortedByTimeDesc(id);
		return new User(id, reviews.get(0).getTime(), reviews.get(reviews.size() - 1).getTime(), reviews.size(),
				super.getKeyWords(reviews),getAverageDescLength(reviews));
	}

	protected List<MovieReview> findMovieReviewsById(String id) {
		List<MovieReview> reviews = dao.findByUserId(id);
		if (reviews.size() == 0) {
			throw new UserNotFoundException(id);
		}
		return reviews;
	}
	
	@Override
	public Map<DescLengthRange, Long> findDescLengthsWithRange(String id, int gap) {
		return findMovieReviewsById(id).stream().map((review) -> review.getContent().length())
				.collect(Collectors.groupingBy((len) -> {
					int level = len / gap;
					DescLengthRange descLengthRange = new DescLengthRange(level * gap, (level + 1) * gap);
					return descLengthRange;
				} , Collectors.counting()));
	}
	
	@Override
	public double getAverageDescLength(List<MovieReview> reviews) {
		return reviews.stream().collect(Collectors.averagingDouble((review) -> review.getContent().length()));
	}

}
