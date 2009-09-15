package dart.blackcat.talker.predicate;

import java.io.Serializable;

/**
 * Predicate interface
 * @author pvyazankin
 *
 */
public interface Predicate extends Serializable {
	
	/**
	 * Set predicate parameters. If there are lesser parameters than specified for this predicate
	 * only first parameters will be set. Otherwise if there are more parameters than specified for
	 * the predicate some of parameters will be ignored.
	 * @param predicates
	 */
	public void setParameters(Predicate... predicates);
	
	/**
	 * True if all of predicate variables were fully initialized.
	 */
	public boolean isInitialized();
	
	/**
	 * Compute predicate function value.
	 * @return function value
	 * @throws PredicateException if one of parameters was not initialized
	 */
	public boolean compute() throws PredicateException;
}
