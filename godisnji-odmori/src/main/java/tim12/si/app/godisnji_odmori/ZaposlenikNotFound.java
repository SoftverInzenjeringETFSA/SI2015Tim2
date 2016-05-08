package tim12.si.app.godisnji_odmori;

public class ZaposlenikNotFound extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ZaposlenikNotFound() {
		super();
		
	}

	public ZaposlenikNotFound(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public ZaposlenikNotFound(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ZaposlenikNotFound(String message) {
		super(message);
		
	}

	public ZaposlenikNotFound(Throwable cause) {
		super(cause);
		
	}

}
