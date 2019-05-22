package fr.excilys.servlet;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.excilys.client.UserException;
import fr.excilys.controller.ComputerController;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.mapper.QueryMapper;
import fr.excilys.servlet.page.Page;
import fr.excilys.servlet.page.PageInformation;
import fr.excilys.servlet.page.PageQuery;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes({ "page", "query" })
public class ListComputer {

	private final ComputerController controller;
	private final QueryMapper mapper;
	private final Logger logger = LoggerFactory.getLogger(ListComputer.class);

	public ListComputer(ComputerController controller, QueryMapper mapper) {
		super();
		this.controller = controller;
		this.mapper = mapper;
	}

	@ModelAttribute("page")
	public Page page() {
		return new Page();
	}

	@ModelAttribute("query")
	public PageQuery query() {
		return new PageQuery();
	}

	@GetMapping
	public Model doGet(Model model, @ModelAttribute("page") Page page, @ModelAttribute("query") PageQuery query) {
		List<ComputerDTO> computers = null;
		PageInformation infos = null;
		try {

			if (Objects.isNull(query.getSearch()) && page.getCurrent() == 1 && page.getLimit() == 20) {
				page = new Page();
				System.out.println("null");
			}

			infos = new PageInformation(controller.count(query.getName()), page.getLimit());
			if (page.getCurrent() < 1 || infos.getPages() < page.getCurrent())
				page.setCurrent(1);
			;

			computers = controller.search(query.getName(), (page.getCurrent() - 1) * page.getLimit(), page.getLimit(),
					mapper.toSqlQuery(query.getName(), query.getOrder(), query.getSens()));

			System.out.println("nb pages " + infos.getPages() + "curent " + page.getCurrent());

		} catch (UserException e) {
			e.printStackTrace();
		}

		model.addAttribute("computers", computers);
		model.addAttribute("page", page);
		model.addAttribute("infos", infos);
		model.addAttribute("query", query);
		System.out.println(query);
		return model;
	}

}
