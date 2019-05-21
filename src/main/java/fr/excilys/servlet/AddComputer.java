package fr.excilys.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.client.UserException;
import fr.excilys.controller.CompanyController;
import fr.excilys.controller.ComputerController;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.model.Companies;

@Controller
@RequestMapping("/computer/add")
public class AddComputer {

	private final Logger logger = LoggerFactory.getLogger(AddComputer.class);
	private final CompanyController companyController;
	private final ComputerController computerController;
	private final ComputerMapper mapper;

	public AddComputer(CompanyController companyController, ComputerController computerController,
			ComputerMapper mapper) {
		super();
		this.companyController = companyController;
		this.computerController = computerController;
		this.mapper = mapper;
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
	public String doPost(@RequestParam String name, @RequestParam String inDate, @RequestParam String outDate,
			@RequestParam String brand) {

		computerController.createComputer(mapper.stringsToDto(name, inDate, outDate, brand));
		return "redirect:/dashboard";

	}
}
