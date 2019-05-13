package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.client.UserException;
import fr.excilys.controller.ComputerController;
import fr.excilys.dto.ComputerDTO;
import fr.excilys.model.Computer;

@WebServlet(urlPatterns = "/dashboard")
public class ListComputer extends HttpServlet {

	private int offset = 0;
	private int limit = 20;
	private int numberComputers;
	private int pageNumber;
	private int pageAt = 0;
	private ComputerController controller = ComputerController.getInstance();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			this.checkPage(req);
			this.checkSize(req);
			numberComputers = controller.countComputers();
			List<ComputerDTO> computers = controller.listComputers(pageAt * limit, limit);
			req.setAttribute("computerNumber", numberComputers);
			req.setAttribute("computers", computers);
			req.setAttribute("offset", offset);
			req.setAttribute("limit", limit);
			pageNumber = (numberComputers % limit) > 0 ? (numberComputers / limit) + 1 : (numberComputers / limit);
			req.setAttribute("pageNumber", pageNumber);
		} catch (UserException e) {
			e.printStackTrace();
		}

		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		rd.forward(req, res);
	}
	
	private void checkPage(HttpServletRequest req) {
		if (req.getParameter("pageAt") != null)
			pageAt = Integer.parseInt(req.getParameter("pageAt")) - 1;
		if (req.getParameter("previous") != null)
			if (req.getParameter("previous").equals("true"))
				if (pageAt > 0)
					pageAt--;
		if (req.getParameter("next") != null)
			if (req.getParameter("next").equals("true"))
				if (pageAt + 1 < pageNumber)
					pageAt++;
	}
	
	private void checkSize(HttpServletRequest req) {
		if (req.getParameter("size") != null)
			limit = Integer.parseInt(req.getParameter("size"));
	}
}
