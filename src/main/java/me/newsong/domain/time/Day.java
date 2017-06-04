package me.newsong.domain.time;

import java.time.LocalDate;

/**
 * Created by SinjinSong on 2017/3/19.
 */
public class Day extends BaseTimeUnit {
    private int year;
    private int month;
    private int day;

    public Day() {
        
    }

    public Day(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Day{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Day day1 = (Day) o;

        if (year != day1.year) return false;
        if (month != day1.month) return false;
        return day == day1.day;
    }

    @Override
    public int hashCode() {
        int result = year;
        result = 31 * result + month;
        result = 31 * result + day;
        return result;
    }


    @Override
    public BaseTimeUnit inc() {
        LocalDate localDate = LocalDate.of(year,month,day).plusDays(1);
        return new Day(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth());
    }

    @Override
    public int compareTo(BaseTimeUnit o) {
        Day rhs = (Day) o;
        int yearDuration = this.year - rhs.year;
        if (yearDuration != 0) {
            return yearDuration;
        } else {
            int monthDuration = this.month - rhs.month;
            if (monthDuration != 0) {
                return monthDuration;
            } else {
                return this.day - rhs.day;
            }
        }
    }
}
