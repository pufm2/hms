package puf.m2.hms.exception;


public class DbException extends Exception {

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	String query;

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	private static final long serialVersionUID = 1L;

	public DbException() {
		super();
	}

	public DbException(String query) {
		super();
		this.query = query;
	}

}
