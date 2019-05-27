package fr.excilys.controller;

import org.springframework.stereotype.Component;

import fr.excilys.exception.UserException;
import fr.excilys.service.CompanyService;

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
