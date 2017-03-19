package me.newsong.flyweight.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import me.newsong.flyweight.utils.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.newsong.flyweight.dao.iface.movie_review.MovieReviewRepository;
import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.service.impl.comp.MovieReviewTimeAscComparator;
import me.newsong.flyweight.utils.PythonUtil;

@Transactional(readOnly = true)
@Service
public abstract class MovieReviewTemplateImpl {
	@Autowired
	@Qualifier("CachedReviews")
	protected MovieReviewRepository dao;
	protected PythonUtil util = PythonUtil.getInstance();
	/**
	 * 模板方法
	 * 
	 * @param id
	 * @return
	 */
	protected abstract List<MovieReview> findMovieReviewById(String id);

	protected List<MovieReview> findMovieReviewsSortedByTime(String id) {
		List<MovieReview> reviews = findMovieReviewById(id);
		Collections.sort(reviews, new MovieReviewTimeAscComparator());
		return reviews;
	}

	public <T> Map<T, Long> findAccumulatedReviewCountsBy(Class<T> unit, String id, Date begin, Date end) {
		Map<T, Long> map = findMovieReviewById(id).stream()
				.filter((review) -> !review.getTime().before(begin) && !review.getTime().after(end))
				.collect(Collectors.groupingBy(SpringContextUtil.getBean(unit.getSimpleName()), Collectors.counting()));
		Map<T, Long> result = new TreeMap<>();
		result.putAll(map);
		long accumulatedCount = 0;
		for (Entry<T, Long> entry : result.entrySet()) {
			entry.setValue(entry.getValue() + accumulatedCount);
			accumulatedCount = entry.getValue();
		}
		return result;
	}
	
	public List<String> getKeyWords(List<MovieReview> reviews) {
		String content = reviews.stream()
			    .map(MovieReview::getContent)
			    .collect(Collectors.joining());
		return util.callForRawList("findKeyWords", Arrays.asList(content,5), String.class);
	}
}
