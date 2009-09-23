package dart.blackcat.talker.syntax;

import java.util.Set;

import dart.blackcat.talker.JTalkerException;
import dart.blackcat.talker.domain.Sentence;

/**
 * Common syntactic analyzer interface
 * @author pvyazankin
 *
 */
public interface SyntacticAnalyzer {

	/**
	 * analyze sentence 
	 * @param sentence to analyze
	 * @return syntactic analysis set
	 * @throws JTalkerException in case errors
	 */
	public Set<SyntacticAnalysis> analyze(Sentence sentence) throws JTalkerException;
}
