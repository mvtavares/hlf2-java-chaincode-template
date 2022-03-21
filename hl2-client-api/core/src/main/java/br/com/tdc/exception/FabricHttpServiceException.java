package br.com.tdc.exception;

public class FabricHttpServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FabricHttpServiceException() {
		super();
	}

	public FabricHttpServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public FabricHttpServiceException(String message) {
		super(message);
	}

	public FabricHttpServiceException(Throwable cause) {
		super(cause);
	}
}
