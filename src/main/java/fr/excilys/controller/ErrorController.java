package fr.excilys.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
	@GetMapping(value = "errors")
	public String renderErrorPage(HttpServletRequest httpRequest) {
		int httpErrorCode = getErrorCode(httpRequest);
		if (httpErrorCode == 403 || httpErrorCode == 404 || httpErrorCode == 500)
			return Integer.toString(httpErrorCode);
		return "dashboard";
	}

	private int getErrorCode(HttpServletRequest httpRequest) {
		return (Integer) httpRequest.getAttribute("javax.servlet.error.status_code");
	}
}
