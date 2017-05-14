package me.newsong.service.func;

import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.time.Season;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

@Component("Season")
public class SeasonDispatcher implements Function<MovieReviewDO, Season>{

	@Override
	public Season apply(MovieReviewDO review) {
		LocalDateTime date = review.getTime();
		Season month = new Season(date.getYear(), date.getMonth().getValue() / 4 + 1);
		return month;
	}

}
