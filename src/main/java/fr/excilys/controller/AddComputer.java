package fr.excilys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.client.UserException;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.service.CompanyService;
import fr.excilys.service.ComputerService;
import fr.excilys.validator.InputValidator;

@Controller
@RequestMapping("/computer/add")
public class AddComputer {

	private final Logger logger = LoggerFactory.getLogger(AddComputer.class);
	private final CompanyService companyService;
	private final ComputerService computerService;
	private final InputValidator validator;
	private final ComputerMapper mapper;

	public AddComputer(CompanyService companyService, ComputerService computerService, InputValidator validator,
			ComputerMapper mapper) {
		super();
		this.companyService = companyService;
		this.computerService = computerService;
		this.validator = validator;
		this.mapper = mapper;
	}

	@GetMapping
	public ModelAndView doGet() {
		ModelAndView modelAndView = new ModelAndView("addComputer");
		try {
			modelAndView.addObject("companies", companyService.search().getCompaniesList());
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return modelAndView;
	}

	@PostMapping
	public String doPost(@ModelAttribute ComputerDTO computer) {
		try {
			validator.check(computer);
			computerService.add(mapper.dtoToBean(computer));
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return "redirect:/dashboard";
	}
}
