package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.excilys.controller.ComputerController;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.mapper.QueryMapper;

@WebServlet(urlPatterns = "/dashboard")
public class ListComputer extends HttpServlet {

	private static final long serialVersionUID = -4657214759745320317L;
	private ComputerController controller;
	private QueryMapper mapper;
	private final Logger logger = LoggerFactory.getLogger(ListComputer.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			Page page = this.getPage(req);
			page.setName(req.getParameter("name"));
			page.setOrder(req.getParameter("order"));
			page.setSens(req.getParameter("sens"));
			int numberComputers = controller.countComputers(page.getName());
			page.setCurrent(numberComputers, req.getParameter("pageAt"), req.getParameter("size"),
					req.getParameter("previous"), req.getParameter("next"));
			List<ComputerDTO> computers = controller.search(page.getName(), page.getOffset(), page.getLimit(),
					mapper.toSqlQuery(page.getName(), page.getOrder(), page.getSens()));
			this.setAttributes(req, computers, numberComputers, page);
			req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, res);
		} catch (Exception e) {
			logger.info(e.getMessage());
			res.sendRedirect("/cdb_project/dashboard");
		}
	}

	public Page getPage(HttpServletRequest req) {
		Page pagination = (Page) req.getSession().getAttribute("page");
		if (Objects.isNull(pagination) || req.getParameter("search") == null && req.getParameter("next") == null
				&& req.getParameter("previous") == null && req.getParameter("pageAt") == null
				&& req.getParameter("size") == null)
			return new Page();
		return pagination;
	}

	public void setAttributes(HttpServletRequest req, List<ComputerDTO> computers, int numberComputers,
			Page pagination) {
		req.setAttribute("offset", pagination.getOffset());
		req.setAttribute("limit", pagination.getLimit());
		req.setAttribute("pageNumber", pagination.getNumber());
		req.setAttribute("computerNumber", numberComputers);
		req.setAttribute("computers", computers);
		req.getSession().setAttribute("page", pagination);
	}

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		controller = wac.getBean(ComputerController.class);
		mapper = wac.getBean(QueryMapper.class);
	}

}
