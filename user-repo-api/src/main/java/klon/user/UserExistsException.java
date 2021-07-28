package klon.user;

public class UserExistsException extends Exception {

	private static final long serialVersionUID = -4504201829999526669L;

	public UserExistsException(String username) {
		super(String.format("User %s exists.", username));
	}
}
