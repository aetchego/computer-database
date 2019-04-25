package fr.excilys.client;

public class UserException extends Exception {

	private static final long serialVersionUID = 1L;
	private String msg;

	public UserException(String msg) {
		this.msg = msg;
		System.out.println(msg);
	}
	
	public String getMsg() {
		return msg;
	}
}
