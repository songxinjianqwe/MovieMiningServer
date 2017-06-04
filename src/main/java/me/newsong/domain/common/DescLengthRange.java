package me.newsong.domain.common;

public class DescLengthRange implements Comparable<DescLengthRange>{
	private int low;
	private int high;
	public DescLengthRange() {
	}
	public DescLengthRange(int low, int high) {
		super();
		this.low = low;
		this.high = high;
	}
	public int getLow() {
		return low;
	}
	public void setLow(int low) {
		this.low = low;
	}
	public int getHigh() {
		return high;
	}
	public void setHigh(int high) {
		this.high = high;
	}
	@Override
	public String toString() {
		return "DescLengthRange [low=" + low + ", high=" + high + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + high;
		result = prime * result + low;
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
		DescLengthRange other = (DescLengthRange) obj;
		if (high != other.high)
			return false;
		if (low != other.low)
			return false;
		return true;
	}

    @Override
    public int compareTo(DescLengthRange o) {
        return this.low - o.low;
    }
}
