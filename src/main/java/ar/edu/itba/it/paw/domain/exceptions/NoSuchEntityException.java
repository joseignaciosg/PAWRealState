package ar.edu.itba.it.paw.domain.exceptions;

public class NoSuchEntityException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoSuchEntityException() {
		super();
	}

	public NoSuchEntityException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public NoSuchEntityException(final String arg0) {
		super(arg0);
	}

	public NoSuchEntityException(final Throwable arg0) {
		super(arg0);
	}

}
