package fr.excilys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.dto.ComputerDTO;
import fr.excilys.exception.UserException;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.service.CompanyService;
import fr.excilys.service.ComputerService;

@Controller
@RequestMapping("/computer/edit")
public class UpdateComputer {

	private final Logger logger = LoggerFactory.getLogger(UpdateComputer.class);
	private final CompanyService companyService;
	private final ComputerService computerService;
	private final ComputerMapper mapper;

	public UpdateComputer(CompanyService companyService, ComputerService computerService, ComputerMapper mapper) {
		super();
		this.companyService = companyService;
		this.computerService = computerService;
		this.mapper = mapper;
	}

	@GetMapping
	public ModelAndView doGet(@ModelAttribute ComputerDTO computer) {
		ModelAndView modelAndView = new ModelAndView("editComputer");
		try {
			modelAndView.addObject("companies", companyService.search().getCompaniesList());
			modelAndView.addObject("computer", computer);
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return modelAndView;
	}

	@PostMapping
	public String doPost(@Validated @ModelAttribute("computer") ComputerDTO computer, BindingResult result,
			Model model) {
		try {
			if (result.hasErrors()) {
				model.addAttribute("companies", companyService.search().getCompaniesList());
				return "editComputer";
			}
			computerService.update(computer.getId(), mapper.dtoToBean(computer));
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return "redirect:/dashboard";
	}
}