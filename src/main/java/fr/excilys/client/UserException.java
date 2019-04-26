package fr.excilys.client;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public UserException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public String getMsg() {
		return msg;
	}
}
