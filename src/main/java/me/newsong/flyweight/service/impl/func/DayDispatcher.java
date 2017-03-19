package me.newsong.flyweight.service.impl.func;

import me.newsong.flyweight.domain.entity.MovieReview;
import me.newsong.flyweight.domain.time.Day;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.function.Function;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@Component("Day")
public class DayDispatcher implements Function<MovieReview, Day> {

    @Override
    public Day apply(MovieReview review) {
        LocalDate date = review.getTime().toInstant().atZone(ZoneId.of("UTC")).toLocalDate();
        Day day = new Day(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
        return day;
    }
}
