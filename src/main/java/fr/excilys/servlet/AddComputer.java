package fr.excilys.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.client.UserException;
import fr.excilys.controller.CompanyController;
import fr.excilys.controller.ComputerController;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.model.Companies;

@Controller
@RequestMapping("/computer/add")
public class AddComputer {

	private final Logger logger = LoggerFactory.getLogger(AddComputer.class);
	private final CompanyController companyController;
	private final ComputerController computerController;

	public AddComputer(CompanyController companyController, ComputerController computerController) {
		super();
		this.companyController = companyController;
		this.computerController = computerController;
	}

	@GetMapping
	public ModelAndView doGet() {
		ModelAndView modelAndView = new ModelAndView("addComputer");
		try {
			Companies companies = companyController.listCompanies();
			modelAndView.addObject("companies", companies.getCompaniesList());
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return modelAndView;
	}

	@PostMapping
	public String doPost(@ModelAttribute ComputerDTO computer) {
		computerController.createComputer(computer);
		return "redirect:/dashboard";
	}
}
