package fr.excilys.controller;

import org.springframework.stereotype.Component;

import fr.excilys.client.UserException;
import fr.excilys.model.Companies;
import fr.excilys.service.CompanyService;

@Component
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	public Companies listCompanies() throws UserException {
		return (companyService.listCompanies());
	}

	public void deleteCompany(int id) throws UserException {
		companyService.deleteCompany(id);
	}
}
