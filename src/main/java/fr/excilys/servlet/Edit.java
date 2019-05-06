package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.controller.ComputerController;

@WebServlet(urlPatterns = "/edit")
public class Edit extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ComputerController controller = ComputerController.getInstance();
		String name = req.getParameter("name");
		String inDate = req.getParameter("introduced");
		String outDate = req.getParameter("discontinued");
		String brand = req.getParameter("brand");
		
		try {
			int id = Integer.parseInt(req.getParameter("id"));
			controller.updateComputer(name, inDate, outDate, brand, id);
		} catch (Exception e) {
			req.setAttribute("error", 1);
		}
		res.sendRedirect("/cdb_project/dashboard");
	}
}
