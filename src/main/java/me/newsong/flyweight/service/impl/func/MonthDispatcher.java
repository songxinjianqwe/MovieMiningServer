package me.newsong.flyweight.service.impl.func;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import me.newsong.flyweight.domain.time.Month;
import me.newsong.flyweight.domain.MovieReview;

@Component("Month")
public class MonthDispatcher implements Function<MovieReview, Month> {

	@Override
	public Month apply(MovieReview review) {
		LocalDate date = review.getTime().toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
		Month month = new Month(date.getYear(), date.getMonth().getValue());
//		System.out.println("Month:"+month);
		return month;
	}

}
