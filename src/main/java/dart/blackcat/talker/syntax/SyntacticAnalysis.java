package dart.blackcat.talker.syntax;

import java.io.Serializable;

import dart.blackcat.talker.domain.Word;

/**
 * Basic syntactic analysis implementation.
 * @author pvyazankin
 *
 */
public class SyntacticAnalysis implements Serializable {

	private static final long serialVersionUID = -344944326430980864L;
	
	
	private Word subject;		// подлежащее
	private Word predicate;		// сказуемое
	
	
	public SyntacticAnalysis(Word subject, Word predicate) {
		this.subject = subject;
		this.predicate = predicate;
	}
	
	public Word getSubject() {
		return subject;
	}
	
	public Word getPredicate() {
		return predicate;
	}
}
