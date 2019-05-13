package fr.excilys.servlet;

public class Page {

	private int current;
	private int limit = 20;
	private int number;
	private int offset;

	public void setCurrent(int number, String current, String limit, String previous, String next) {
		this.setLimit(limit);
		this.setNumber(number, this.limit);
		if (current != null) {
			this.current = Integer.parseInt(current) - 1;
		}
		if (previous != null)
			if (previous.equals("true"))
				if (this.current > 0)
					this.current--;
		if (next != null)
			if (next.equals("true")) {
				if (this.current + 1 < this.number)
					this.current++;
			}
		if (this.current >= this.number)
			this.current = this.number - 1;
		this.setOffset(this.current, this.limit);
	}

	private void setLimit(String limit) {
		if (limit != null)
			this.limit = Integer.parseInt(limit);
	}

	private void setOffset(int current, int limit) {
		this.offset = current * limit;
	}

	private void setNumber(int number, int limit) {
		this.number = (number % limit) > 0 ? (number / limit) + 1 : (number / limit);
	}

	public int getCurrent() {
		return current;
	}

	public int getLimit() {
		return limit;
	}

	public int getNumber() {
		return number;
	}

	public int getOffset() {
		return offset;
	}
}
