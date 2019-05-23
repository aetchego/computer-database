package fr.excilys.controller;

import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import fr.excilys.client.UserException;
import fr.excilys.controller.page.Page;
import fr.excilys.controller.page.PageInformation;
import fr.excilys.controller.page.PageQuery;
import fr.excilys.mapper.ComputerMapper;
import fr.excilys.mapper.QueryMapper;
import fr.excilys.service.ComputerService;

@Controller
@RequestMapping("/dashboard")
@SessionAttributes({ "page", "query" })
public class SearchComputer {

	private final ComputerService service;
	private final QueryMapper queryMapper;
	private final ComputerMapper computerMapper;
	private final Logger logger = LoggerFactory.getLogger(SearchComputer.class);
	private PageInformation infos;

	public SearchComputer(ComputerService service, QueryMapper mapper, ComputerMapper computerMapper) {
		super();
		this.service = service;
		this.queryMapper = mapper;
		this.computerMapper = computerMapper;
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
		try {
			if (Objects.isNull(query.getSearch()) && page.getCurrent() == 1 && page.getLimit() == 20)
				page = new Page();
			model.addAttribute("infos", (infos = new PageInformation(service.count(query.getName()), page.getLimit())));
			if (page.getCurrent() < 1 || infos.getPages() < page.getCurrent())
				page.setCurrent(1);
			model.addAttribute("computers",
					service.search(query.getName(), (page.getCurrent() - 1) * page.getLimit(), page.getLimit(),
							queryMapper.toSqlQuery(query.getName(), query.getOrder(), query.getSens())).stream()
							.map(computerMapper::beanToDto).collect(Collectors.toList()));
			model.addAttribute("infos", infos);
		} catch (UserException e) {
			logger.info(e.getMessage());
		}
		return model;
	}

}
