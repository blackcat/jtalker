package dart.blackcat.talker.domain;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dart.blackcat.talker.JTalkerException;


public class MorphologyAnalyzerQueue implements MorphologyAnalyzer {
	
	private Set<MorphologyAnalyzer> analyzers = new LinkedHashSet<MorphologyAnalyzer>();

	@Override
	public Set<MorphologyAnalysis> analyze(String word) throws JTalkerException {
		Set<MorphologyAnalysis> result = null;
		for (MorphologyAnalyzer analyzer : analyzers) {
			result = analyzer.analyze(word);
			if (result != null) {
				return result;
			}
		}
		return null;
	}
	
	public void setAnalyzers(List<MorphologyAnalyzer> analyzers) {
		this.analyzers.addAll(analyzers);
	}

}
