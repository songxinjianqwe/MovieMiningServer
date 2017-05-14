package me.newsong.service.func;

import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.time.Month;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;


@Component("Month")
public class MonthDispatcher implements Function<MovieReviewDO, Month> {

	@Override
	public Month apply(MovieReviewDO review) {
		LocalDateTime date = review.getTime();
		Month month = new Month(date.getYear(), date.getMonth().getValue());
		return month;
	}

}
