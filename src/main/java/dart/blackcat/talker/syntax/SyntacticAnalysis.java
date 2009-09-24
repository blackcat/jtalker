package dart.blackcat.talker.syntax;

import java.io.Serializable;

import dart.blackcat.talker.domain.Sentence;
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
	private Sentence sentence;
	
	
	public SyntacticAnalysis(Word subject, Word predicate, Sentence sentence) {
		this.subject = subject;
		this.predicate = predicate;
		this.sentence = sentence;
	}
	
	public Word getSubject() {
		return subject;
	}
	
	public Word getPredicate() {
		return predicate;
	}
	
	public Sentence getSentence() {
		return sentence;
	}
	
	@Override
	public String toString() {
		return sentence + " subject=" + subject + " predicate=" + predicate;
	}
}
