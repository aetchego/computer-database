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
import fr.excilys.model.Computer;



@WebServlet(urlPatterns = "/dashboard")
public class ListComputer extends HttpServlet {
	
	private ComputerController controller = ComputerController.getInstance();

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		try {
			List<Computer> computers = controller.listComputers(0, 150);
			System.out.println(computers.get(0).toLittleString());
			req.setAttribute("computerNumber", computers.size());
			req.setAttribute("computers", computers);
		} catch (UserException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
		rd.forward(req, res);
	}
}
