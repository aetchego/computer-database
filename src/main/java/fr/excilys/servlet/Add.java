package fr.excilys.servlet;

import java.io.IOException;

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
import fr.excilys.mapper.ComputerMapper;

@WebServlet(urlPatterns = "/add")
public class Add extends HttpServlet {

	private static final long serialVersionUID = -8271662913378846461L;
	private ComputerMapper mapper;
	private ComputerController controller;
	private final Logger logger = LoggerFactory.getLogger(Add.class);

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			controller.createComputer(mapper.stringsToDto(req.getParameter("name"), req.getParameter("inDate"),
					req.getParameter("outDate"), req.getParameter("brand")));
			res.sendRedirect("/cdb_project/dashboard");
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		controller = wac.getBean(ComputerController.class);
		mapper = wac.getBean(ComputerMapper.class);
	}
}
