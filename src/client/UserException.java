package client;

public class UserException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserException(String msg) {
		System.out.println(msg);
	}
}
