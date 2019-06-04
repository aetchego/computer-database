package fr.excilys.console.controller;

import org.springframework.stereotype.Component;

import fr.excilys.services.exception.UserException;
import fr.excilys.services.services.CompanyService;

@Component
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	public void delete(int id) throws UserException {
		companyService.delete(id);
	}
}
