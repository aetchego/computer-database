package fr.excilys.webapp.pagination;

public class PageInformation {

	private Integer computers;
	private Integer pages;

	public PageInformation(Integer computer, Integer limit) {
		super();
		this.computers = computer;
		this.pages = (computer % limit) > 0 ? (computer / limit) + 1 : (computer / limit);
	}

	public Integer getComputers() {
		return computers;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	@Override
	public String toString() {
		return "PageInformation [computers=" + computers + ", pages=" + pages + "]";
	}

}
