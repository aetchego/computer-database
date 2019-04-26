package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SquareServlet extends HttpServlet {

public void doGet(HttpServletRequest req, HttpServletResponse res) {
		
		/* obtenir les parametres d'url */
		//int result = Integer.parseInt(req.getParameter("result"));
		
		/* SESSION STYLE 
		 * HttpSession session = req.getSession();
		 * int result = (int)session.getAttribute("result");
		 * remove attribute 
		 * session.removeAttribute("result");
		 * 
		 * 
		 * */
		int result = 0;
		Cookie cookies[] = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("result"))
				result = Integer.parseInt(c.getValue());
		}
		try {
			res.getWriter().println("Result is " + result * result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
