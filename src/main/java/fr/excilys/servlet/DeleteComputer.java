package fr.excilys.servlet;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.excilys.client.UserException;
import fr.excilys.controller.ComputerController;

@Controller
@RequestMapping("/computer/delete")
public class DeleteComputer {

	private ComputerController controller;
	private final Logger logger = LoggerFactory.getLogger(DeleteComputer.class);

	public DeleteComputer(ComputerController controller) {
		super();
		this.controller = controller;
	}

	@PostMapping
	public String doPost(@RequestParam List<Integer> selection) {
		for (Integer e : selection) {
			try {
				controller.deleteComputer(e);
			} catch (UserException e1) {
				logger.info(e1.getMessage());
			}
		}
		return "redirect:/dashboard";
	}
}
