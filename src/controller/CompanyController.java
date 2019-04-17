package controller;

import service.CompanyService;

public class CompanyController {

	public static void listCompanies() {
		CompanyService.listCompanies();
	}
}
