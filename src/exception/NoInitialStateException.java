package exception;

public class NoInitialStateException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 7623217580830816768L;
	private String msg = "";

	public NoInitialStateException(final String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

}
