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
import fr.excilys.controller.ComputerController;

@WebServlet(urlPatterns = "/delete")
public class DeleteComputer extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private ComputerController controller = ComputerController.getInstance();

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		List<String> list = Arrays.asList(req.getParameter("selection").split(","));
		for (String e : list) {
			try {
				controller.deleteComputer(Integer.parseInt(e));
			} catch (NumberFormatException | UserException e1) {
				e1.printStackTrace();
			}
		}
			res.sendRedirect("/cdb_project/dashboard");
	}
}
