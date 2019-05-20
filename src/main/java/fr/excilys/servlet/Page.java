package fr.excilys.servlet;

import java.io.Serializable;

public class Page implements Serializable {

	private static final long serialVersionUID = 8506164897885900230L;
	private int current;
	private int limit = 20;
	private int number;
	private int offset;
	private String name;
	private String order;
	private String sens;

	public void setName(String name) {
		if (name != null)
			this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setOrder(String order) {
		if (order != null)
			this.order = order;
	}

	public String getOrder() {
		return this.order;
	}

	public void setSens(String sens) {
		if (sens != null)
			this.sens = sens;
	}

	public String getSens() {
		return this.sens;
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

	public void setCurrent(int number, String current, String limit, String previous, String next) {
		this.setLimit(limit);
		this.setNumber(number, this.limit);
		if (current != null)
			this.current = Integer.parseInt(current) - 1;
		if (previous != null && previous.equals("true") && this.current > 0)
			this.current--;
		if (next != null && next.equals("true") && this.current + 1 < this.number)
			this.current++;
		if (this.current >= this.number)
			this.current = this.number - 1;
		this.setOffset(this.current, this.limit);
	}

	private void setLimit(String limit) {
		if (limit != null)
			this.limit = Integer.parseInt(limit);
	}

	private void setNumber(int number, int limit) {
		this.number = (number % limit) > 0 ? (number / limit) + 1 : (number / limit);
	}

	private void setOffset(int current, int limit) {
		this.offset = current * limit;
	}
}
