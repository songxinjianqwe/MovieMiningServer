package me.newsong.domain.time;

import me.newsong.enums.TimeUnit;

import java.time.LocalDateTime;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public abstract class BaseTimeUnit implements Comparable<BaseTimeUnit> {
    abstract public BaseTimeUnit inc();

    public static BaseTimeUnit from(TimeUnit unit, LocalDateTime localDateTime) {
        if (unit == TimeUnit.Month) {
            return new Month(localDateTime.getYear(), localDateTime.getMonthValue());
        } else if (unit == TimeUnit.Day) {
            return new Day(localDateTime.getYear(), localDateTime.getMonthValue(), localDateTime.getDayOfMonth());
        } else if (unit == TimeUnit.Season) {
            return new Season(localDateTime.getYear(), (localDateTime.getMonthValue() / 4) + 1);
        } else {
            return null;
        }
    }

}
