package puf.m2.hms.exception;

public class PhysicianException extends Exception {

	private String name, role;
	private boolean available;

	private static final long serialVersionUID = 1L;

	public PhysicianException(String name, String role, boolean available) {
		super();
		this.name = name;
		this.role = role;
		this.available = available;
	}

	public PhysicianException(int id) {
	}

	public String getName() {
		return name;
	}

	public String getRole() {
		return role;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
