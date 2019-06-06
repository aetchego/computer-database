package fr.excilys.webapp.controller.rest.validation;

import fr.excilys.webapp.controller.rest.exception.ResourceNotFoundException;

public class Preconditions {
	public static <T> T checkFound(T resource) {
		if (resource == null) {
			throw new ResourceNotFoundException();
		}
		return resource;
	}
}
