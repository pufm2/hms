package puf.m2.hms.exception;

public class PatientException extends Exception {

	public PatientException(int id) {
		super();
		this.id = id;
	}

	private static final long serialVersionUID = 1L;

	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
