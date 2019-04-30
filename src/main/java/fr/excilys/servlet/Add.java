package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.controller.ComputerController;

@WebServlet(urlPatterns = "/add")
public class Add extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ComputerController controller = ComputerController.getInstance();
		String name = req.getParameter("name");
		String inDate = req.getParameter("inDate");
		String outDate = req.getParameter("outDate");
		String brand = req.getParameter("brand");
		controller.createComputer(name, inDate, outDate, brand);
		// System.out.println(name + inDate + outDate + brand);
		RequestDispatcher rd = req.getRequestDispatcher("/dashboard");
		rd.forward(req, res);
	}
}
