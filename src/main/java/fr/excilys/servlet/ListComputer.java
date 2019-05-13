package fr.excilys.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.client.UserException;
import fr.excilys.controller.ComputerController;
import fr.excilys.dto.ComputerDTO;

@WebServlet(urlPatterns = "/dashboard")
public class ListComputer extends HttpServlet {

	private ComputerController controller = ComputerController.getInstance();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		try {
			int numberComputers = controller.countComputers();
			Page pagination = (Page)req.getSession().getAttribute("page");
			pagination = Objects.nonNull(pagination) ? pagination : new Page();
			pagination.setCurrent(numberComputers, req.getParameter("pageAt"), req.getParameter("size"),
			req.getParameter("previous"), req.getParameter("next"));
			req.getSession().setAttribute("page", pagination);
			List<ComputerDTO> computers = controller.listComputers(pagination.getOffset(), pagination.getLimit());
			req.setAttribute("computers", computers);
			req.setAttribute("computerNumber", numberComputers);
			req.setAttribute("offset", pagination.getOffset());
			req.setAttribute("limit", pagination.getLimit());
			req.setAttribute("pageNumber", pagination.getNumber());
		} catch (UserException e) {
			System.out.println(e.getMessage());
		}
		req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, res);
	}

}
