package fr.excilys.webapp.pagination;

public class PageQuery {

	private String name;
	private String order;
	private String sens;
	private String search;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getSens() {
		return sens;
	}

	public void setSens(String sens) {
		this.sens = sens;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String toString() {
		return "PageQuery [name=" + name + ", order=" + order + ", sens=" + sens + "]";
	}

}
