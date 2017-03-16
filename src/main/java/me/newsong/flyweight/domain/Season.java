package me.newsong.flyweight.domain;

public class Season implements Comparable<Season>{
	private int year;
	private int season;

	public Season() {
		// TODO Auto-generated constructor stub
	}

	public Season(int year, int season) {
		super();
		this.year = year;
		this.season = season;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	@Override
	public String toString() {
		return "Season [year=" + year + ", season=" + season + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + season;
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
		Season other = (Season) obj;
		if (season != other.season)
			return false;
		if (year != other.year)
			return false;
		return true;
	}

	@Override
	public int compareTo(Season rhs) {
		int yearDuration = this.year - rhs.year;
		if(yearDuration != 0){
			return yearDuration;
		}else{
			return this.season - rhs.season;
		}
	}

}
