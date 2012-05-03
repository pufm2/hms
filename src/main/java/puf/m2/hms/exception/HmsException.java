package puf.m2.hms.exception;

public class HmsException extends Exception {

    private static final long serialVersionUID = 1199072650805830161L;

    private String message;
    
    public HmsException(Throwable cause) {
        super(cause);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
