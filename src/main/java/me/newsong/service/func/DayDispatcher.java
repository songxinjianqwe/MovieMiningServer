package me.newsong.service.func;

import me.newsong.domain.entity.MovieReviewDO;
import me.newsong.domain.time.Day;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;

/**
 * Created by SinjinSong on 2017/3/19.
 */
@Component("Day")
public class DayDispatcher implements Function<MovieReviewDO, Day> {

    @Override
    public Day apply(MovieReviewDO review) {
        LocalDateTime date = review.getTime();
        Day day = new Day(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth());
        return day;
    }
}
