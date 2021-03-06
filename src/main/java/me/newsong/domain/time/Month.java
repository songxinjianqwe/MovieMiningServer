package me.newsong.domain.time;

import java.time.LocalDate;

public class Month extends BaseTimeUnit  {
    private int year;
    private int month;

    public Month() {
    }

    public Month(int year, int month) {
        super();
        this.year = year;
        this.month = month;
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

    @Override
    public String toString() {
        return "Month [year=" + year + ", month=" + month + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + month;
        result = prime * result + year;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Month other = (Month) obj;
        if (month != other.month)
            return false;
        if (year != other.year)
            return false;
        return true;
    }


    @Override
    public BaseTimeUnit inc() {
        LocalDate localDate = LocalDate.of(year,month,1).plusMonths(1);
        return new Month(localDate.getYear(),localDate.getMonthValue());
    }

    @Override
    public int compareTo(BaseTimeUnit o) {
        Month rhs = (Month) o;
        int yearDuration = this.year - rhs.year;
        if (yearDuration != 0) {
            return yearDuration;
        } else {
            return this.month - rhs.month;
        }
    }
}
