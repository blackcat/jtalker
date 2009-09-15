package dart.blackcat.talker.predicate;

/**
 * Root exception class for predicate package.
 * @author pvyazankin
 *
 */
public class PredicateException extends Exception {

	private static final long serialVersionUID = 8930867082250136640L;

	public PredicateException() {
	}

	public PredicateException(String message) {
		super(message);
	}

	public PredicateException(Throwable cause) {
		super(cause);
	}

	public PredicateException(String message, Throwable cause) {
		super(message, cause);
	}

}
