package dart.blackcat.talker.aot.dao;

import dart.blackcat.talker.aot.AotException;

public class DatabaseIntegrityViolationException extends AotException {

	private static final long serialVersionUID = -5983435275773438286L;

	public DatabaseIntegrityViolationException() {
		super();
	}

	public DatabaseIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DatabaseIntegrityViolationException(String message) {
		super(message);
	}

	public DatabaseIntegrityViolationException(Throwable cause) {
		super(cause);
	}

}
