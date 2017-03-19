package me.newsong.flyweight.service.impl.comp;

import java.util.Comparator;

import me.newsong.flyweight.domain.MovieReview;

public class MovieReviewTimeAscComparator implements Comparator<MovieReview> {
	
	@Override
	public int compare(MovieReview r1, MovieReview r2) {
		if (r1.getTime().after(r2.getTime())) {
			return 1;
		} else if (r1.getTime().before(r2.getTime())) {
			return -1;
		} else {
			return 0;
		}
	}

}
