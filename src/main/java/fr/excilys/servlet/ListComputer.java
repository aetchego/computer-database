package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import fr.excilys.controller.ComputerController;

@WebServlet(urlPatterns = "/dashboard")
public class ListComputer extends HttpServlet {
	
	private ComputerController controller = ComputerController.getInstance();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setAttribute("computerNumber", 3);
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		rd.forward(req, res);
	}
}
