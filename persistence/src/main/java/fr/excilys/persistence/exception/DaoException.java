package fr.excilys.persistence.exception;

public class DaoException extends Exception {

	private static final long serialVersionUID = 1L;
	private final String msg;

	public DaoException(String msg) {
		super(msg);
		this.msg = msg;
	}

	@Override
	public String getMessage() {
		return msg;
	}
}
