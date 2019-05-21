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

import fr.excilys.client.UserException;
import fr.excilys.controller.CompanyController;
import fr.excilys.model.Companies;

@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {

	private static final long serialVersionUID = 3884356107883326043L;
	private final Logger logger = LoggerFactory.getLogger(AddComputer.class);
	private CompanyController controller;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			Companies companies = controller.listCompanies();
			req.setAttribute("companies", companies.getCompaniesList());
			req.getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(req, res);
		} catch (UserException | ServletException | IOException e) {
			logger.info(e.getMessage());
		}
	}

	@Override
	public void init() throws ServletException {
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		controller = wac.getBean(CompanyController.class);
	}
}
