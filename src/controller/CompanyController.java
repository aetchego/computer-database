package controller;

import service.CompanyService;

public class CompanyController {

	private static CompanyController instance = null;
	private static CompanyService companyService = CompanyService.getInstance();
	
	private CompanyController() {	
	}
	
	public static CompanyController getInstance() {
		if (instance == null)
			instance = new CompanyController();
		return instance;
	}
	
	public void listCompanies() {
		companyService.listCompanies();
	}
}
