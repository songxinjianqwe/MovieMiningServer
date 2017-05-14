package me.newsong.service.impl;

import me.newsong.domain.common.DescLengthRange;
import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.entity.User;
import me.newsong.exception.UserNotFoundException;
import me.newsong.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@Service
public class UserServiceImpl extends MovieReviewTemplateImpl implements UserService {
	
	@Override
	public List<String> findAllIds() {
		return movieReviewDOMapper.findAllUserIds();
	}

	@Override
	public User findUserById(String id) {
		List<MovieReviewDO> reviews = findMovieReviewDOsSortedByTimeDesc(id);
		return new User(id, reviews.get(0).getTime(), reviews.get(reviews.size() - 1).getTime(), reviews.size(),
				super.getKeyWords(reviews),getAverageDescLength(reviews));
	}

	protected List<MovieReviewDO> findMovieReviewDOsById(String id) {
		List<MovieReviewDO> reviews = movieReviewDOMapper.findByUserId(id);
		if (reviews.size() == 0) {
			throw new UserNotFoundException(id);
		}
		return reviews;
	}
	
	@Override
	public Map<DescLengthRange, Long> findDescLengthsWithRange(String id, int gap) {
		return findMovieReviewDOsById(id).stream().map((review) -> review.getContent().length())
				.collect(Collectors.groupingBy((len) -> {
					int level = len / gap;
					DescLengthRange descLengthRange = new DescLengthRange(level * gap, (level + 1) * gap);
					return descLengthRange;
				} , Collectors.counting()));
	}
	
	@Override
	public double getAverageDescLength(List<MovieReviewDO> reviews) {
		return reviews.stream().collect(Collectors.averagingDouble((review) -> review.getContent().length()));
	}

}
