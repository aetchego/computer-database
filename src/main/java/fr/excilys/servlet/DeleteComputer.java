package fr.excilys.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import fr.excilys.client.UserException;
import fr.excilys.controller.ComputerController;
import fr.excilys.mapper.ComputerMapper;

@WebServlet(urlPatterns = "/delete")
public class DeleteComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ComputerController controller;
	private final Logger logger = LoggerFactory.getLogger(DeleteComputer.class);

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		controller = wac.getBean(ComputerController.class);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		List<String> list = Arrays.asList(req.getParameter("selection").split(","));
		for (String e : list) {
			try {
				controller.deleteComputer(Integer.parseInt(e));
			} catch (NumberFormatException | UserException e1) {
				logger.info(e1.getMessage());
			}
		} try {
			res.sendRedirect("/cdb_project/dashboard");
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
	}
}
