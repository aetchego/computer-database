package fr.excilys.servlet;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.client.UserException;
import fr.excilys.controller.CompanyController;
import fr.excilys.controller.ComputerController;
import fr.excilys.model.Companies;

@WebServlet(urlPatterns = "/editComputer")
public class EditComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ComputerController controller = ComputerController.getInstance();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		CompanyController controller = CompanyController.getInstance();
		try {
			Companies companies = controller.listCompanies();
			req.setAttribute("companies", companies.getCompanies());
			req.setAttribute("id", req.getParameter("id"));
			req.setAttribute("name", req.getParameter("name"));
			req.setAttribute("in", req.getParameter("in"));
			req.setAttribute("out", req.getParameter("out"));
			req.setAttribute("company", req.getParameter("company"));
		} catch (UserException e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/editComputer.jsp");
		rd.forward(req, res);
	}
}

