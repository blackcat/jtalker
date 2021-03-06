package dart.blackcat.talker.morph;

import java.util.Set;

import dart.blackcat.talker.JTalkerException;

/**
 * Commot morphology analyzer interface
 * @author pvyazankin
 *
 */
public interface MorphologyAnalyzer {

	/**
	 * return 
	 * @param word word to analyze
	 * @return {@link Set} of {@link MorphologyAnalysis} or null if can't analyze.
	 * @throws JTalkerException if there is any problem
	 */
	public Set<MorphologyAnalysis> analyze(String word) throws JTalkerException;
}
