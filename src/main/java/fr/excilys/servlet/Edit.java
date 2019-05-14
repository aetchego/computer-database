package fr.excilys.servlet;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.excilys.controller.ComputerController;
import fr.excilys.mapper.ComputerMapper;

@WebServlet(urlPatterns = "/edit")
public class Edit extends HttpServlet {

	private static final long serialVersionUID = -3458370544877737983L;
	private final ComputerMapper mapper = ComputerMapper.getInstance();
	private final ComputerController controller = ComputerController.getInstance();
	private final Logger logger = LoggerFactory.getLogger(Edit.class);
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) {
		try {
			controller.updateComputer(Integer.parseInt(req.getParameter("id")), mapper.StringsToDTO(req.getParameter("name"), req.getParameter("introduced"),
			req.getParameter("discontinued"), req.getParameter("brand")));
			res.sendRedirect("/cdb_project/dashboard");
		} catch (IOException | NumberFormatException e) {
			logger.info(e.getMessage());
		}
	}
}
