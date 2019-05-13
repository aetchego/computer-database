package fr.excilys.client;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String msg;

	public UserException(String msg) {
		super(msg);
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
