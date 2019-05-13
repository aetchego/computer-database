package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.controller.ComputerController;
import fr.excilys.mapper.ComputerMapper;

@WebServlet(urlPatterns = "/add")
public class Add extends HttpServlet {

	private final ComputerMapper mapper = ComputerMapper.getInstance();
	private final ComputerController controller = ComputerController.getInstance();
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			controller.createComputer(mapper.StringsToDTO(req.getParameter("name"), req.getParameter("inDate"),
			req.getParameter("outDate"), req.getParameter("brand")));
		} catch (Exception e) {
			req.setAttribute("error", 1);
		}
		res.sendRedirect("/cdb_project/dashboard");
	}
}
