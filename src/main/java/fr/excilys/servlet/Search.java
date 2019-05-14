package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.excilys.controller.ComputerController;
import fr.excilys.dto.ComputerDTO;

@WebServlet(urlPatterns = "/search")
public class Search extends HttpServlet {
	
	private static final long serialVersionUID = -1304806379012931426L;
	private final Logger logger = LoggerFactory.getLogger(ListComputer.class);
	private final ComputerController controller = ComputerController.getInstance();
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) {
		try {
			List<ComputerDTO> computers = req.getParameter("searchComputer") != null ? controller.search(req.getParameter("name"), "computer.name") : controller.search(req.getParameter("name"), "company.name");
			req.setAttribute("computers", computers);
			req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, res);	
		} catch (ServletException | IOException e) {
			logger.info(e.getMessage());
		}
	}
}
