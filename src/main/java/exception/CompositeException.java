package exception;

public class CompositeException extends Exception {
	
	private static final long serialVersionUID = -6206284372881611005L;

	public CompositeException() {
		super();
	}

	public CompositeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CompositeException(String message, Throwable cause) {
		super(message, cause);
	}

	public CompositeException(String message) {
		super(message);
	}

	public CompositeException(Throwable cause) {
		super(cause);
	}

}
