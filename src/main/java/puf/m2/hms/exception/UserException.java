package puf.m2.hms.exception;


public class UserException extends Exception {

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private static final long serialVersionUID = 1L;

	String username, password;

	public UserException(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
}
