package fr.excilys.webapp.pagination;

public class Page {

	private int current = 1;
	private int limit = 20;

	public int getCurrent() {
		return current;
	}

	public void setCurrent(int current) {
		this.current = current;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	@Override
	public String toString() {
		return "Page [current=" + current + ", limit=" + limit + "]";
	}

}
