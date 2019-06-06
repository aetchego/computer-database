package fr.excilys.webapp.controller.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.excilys.services.exception.UserException;
import fr.excilys.services.services.ComputerService;

@Controller
@RequestMapping("/computer/delete")
public class DeleteComputer {

	private ComputerService service;
	private final Logger logger = LoggerFactory.getLogger(DeleteComputer.class);

	public DeleteComputer(ComputerService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public String doPost(@RequestParam List<Integer> selection) {
		for (Integer e : selection) {
			try {
				service.delete(e);
			} catch (UserException e1) {
				logger.info(e1.getMessage());
			}
		}
		return "redirect:/dashboard";
	}
}
