package fr.excilys.dto;

import org.springframework.lang.NonNull;

public class ComputerDTO {

	@NonNull
	private Integer id;
	private String name;
	private String introduced;
	private String discontinued;
	private String brand;

	public String getBrand() {
		return brand;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public Integer getId() {
		return id;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getName() {
		return name;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ComputerDTO [id=" + id + ", name=" + name + ", introduced=" + introduced + ", discontinued="
				+ discontinued + ", brand=" + brand + "]";
	}
}
