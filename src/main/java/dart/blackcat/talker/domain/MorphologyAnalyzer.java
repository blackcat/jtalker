package dart.blackcat.talker.domain;

import java.util.Set;

public interface MorphologyAnalyzer {

	/**
	 * return 
	 * @param word word to analyze
	 * @return {@link Set} of {@link MorphologyAnalysis} or null if can't analyze.
	 */
	public Set<MorphologyAnalysis> analyze(String word);
}
