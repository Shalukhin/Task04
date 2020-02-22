package exception;

public class CalculatotException extends Exception {

	private static final long serialVersionUID = -6865239663704721214L;

	public CalculatotException() {
		super();
	}

	public CalculatotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CalculatotException(String message, Throwable cause) {
		super(message, cause);
	}

	public CalculatotException(String message) {
		super(message);
	}

	public CalculatotException(Throwable cause) {
		super(cause);
	}

}
