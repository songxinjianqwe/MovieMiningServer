package me.newsong.service.comp;

import me.newsong.domain.entity.MovieReviewDO;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component("Time")
public class MovieReviewTimeDescComparator implements Comparator<MovieReviewDO> {
	
	@Override
	public int compare(MovieReviewDO r1, MovieReviewDO r2) {
		if (r1.getTime().isBefore(r2.getTime())) {
			return 1;
		} else if (r1.getTime().isAfter(r2.getTime())) {
			return -1;
		} else {
			return 0;
		}
	}
}
