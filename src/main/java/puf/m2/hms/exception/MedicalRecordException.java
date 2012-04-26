package puf.m2.hms.exception;

public class MedicalRecordException extends Exception {

	/**
	 * 
	 */
	String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	private static final long serialVersionUID = 1L;

}
