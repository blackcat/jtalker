package dart.blackcat.talker.syntax;

public class SyntacticException extends Exception {

	private static final long serialVersionUID = 4454511382324112474L;

	public SyntacticException() {
	}

	public SyntacticException(String message) {
		super(message);
	}

	public SyntacticException(Throwable cause) {
		super(cause);
	}

	public SyntacticException(String message, Throwable cause) {
		super(message, cause);
	}

}
