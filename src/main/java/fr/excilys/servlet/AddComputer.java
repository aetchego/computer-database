package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.client.UserException;
import fr.excilys.controller.CompanyController;
import fr.excilys.model.Companies;

@WebServlet(urlPatterns = "/addComputer")
public class AddComputer extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		CompanyController controller = CompanyController.getInstance();
		try {
			Companies companies = controller.listCompanies();
			req.setAttribute("companies", companies.getCompanies());
		} catch (UserException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/addComputer.jsp");
		rd.forward(req, res);
	}
}
