package me.newsong.util.movie;

public class Index {
	private int pageIndex;
	private int movieIndex;
	public Index() {
	}
	
	public Index(int pageIndex, int movieIndex) {
		super();
		this.pageIndex = pageIndex;
		this.movieIndex = movieIndex;
	}

	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getMovieIndex() {
		return movieIndex;
	}
	public void setMovieIndex(int movieIndex) {
		this.movieIndex = movieIndex;
	}

	@Override
	public String toString() {
		return "Index [pageIndex=" + pageIndex + ", movieIndex=" + movieIndex + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + movieIndex;
		result = prime * result + pageIndex;
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
		Index other = (Index) obj;
		if (movieIndex != other.movieIndex)
			return false;
		if (pageIndex != other.pageIndex)
			return false;
		return true;
	}
	
}
