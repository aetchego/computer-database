package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		int x = Integer.parseInt(req.getParameter("num1"));
		int y = Integer.parseInt(req.getParameter("num2"));
		int result = x + y;
		try {
			res.getWriter().println("result is " + result);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Cookie cookie = new Cookie("result", result + "");
		res.addCookie(cookie);
		
		/*SESSION STYLE
			HttpSession session = req.getSession();
			session.setAttribute("result", result);
		*/
		//res.sendRedirect("sq?result=" + result); // technique "URL Rewriting"
		res.sendRedirect("sq");
		
		/*req.setAttribute("result", result);
		RequestDispatcher rd = req.getRequestDispatcher("/sq");
		rd.forward(req, res);*/
		/* 
		 * 2 manières d'appeler une autre servlet 
		 * RequestDispatcher --> entre 2 servlets du même website 
		 * SendRedirect --> redirection vers une autre adresse, il y'a donc 2 objets req
		 * */
	}
}
