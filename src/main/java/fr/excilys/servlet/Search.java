package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.controller.ComputerController;
import fr.excilys.model.Computer;

@WebServlet(urlPatterns = "/search")
public class Search extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ComputerController controller = ComputerController.getInstance();
		String name = req.getParameter("name");
		List<Computer> computers = req.getParameter("searchComputer") != null ? controller.search(name, "computer.name") : controller.search(name, "company.name");
		
		req.setAttribute("computers", computers);
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		rd.forward(req, res);
	}
}
