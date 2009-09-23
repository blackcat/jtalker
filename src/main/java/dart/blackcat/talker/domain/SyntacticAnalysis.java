package dart.blackcat.talker.domain;

import java.io.Serializable;

/**
 * Basic syntactic analysis implementation.
 * @author pvyazankin
 *
 */
public class SyntacticAnalysis implements Serializable {

	private static final long serialVersionUID = -344944326430980864L;
	
	
	private String subject;		// подлежащее
	private String predicate;	// сказуемое
	
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getPredicate() {
		return predicate;
	}
	
	public void setPredicate(String predicate) {
		this.predicate = predicate;
	}
}
