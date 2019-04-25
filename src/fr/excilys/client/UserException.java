package fr.excilys.client;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserException(String msg) {
		System.out.println(msg);
	}
}
