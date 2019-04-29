package fr.excilys.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<String> test = new ArrayList<>();
		test.add("Alizé");
		test.add("Agathe");
		test.add("Abou");
		req.setAttribute("friends", test);
		RequestDispatcher rd = req.getRequestDispatcher("test.jsp");
		rd.forward(req, res);
	}
}
