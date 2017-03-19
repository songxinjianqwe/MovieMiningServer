package me.newsong.flyweight.service.impl.func;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import me.newsong.flyweight.domain.MovieReview;
import me.newsong.flyweight.domain.time.Season;
@Component("Season")
public class SeasonDispatcher implements Function<MovieReview, Season>{

	@Override
	public Season apply(MovieReview review) {
		LocalDate date = review.getTime().toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
		Season month = new Season(date.getYear(), date.getMonth().getValue() / 4 + 1);
		return month;
	}

}
