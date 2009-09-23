package dart.blackcat.talker.syntax;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import dart.blackcat.talker.JTalkerException;
import dart.blackcat.talker.domain.Sentence;

public class SyntacticAnalyzerQueue implements SyntacticAnalyzer {

	private Set<SyntacticAnalyzer> analyzers = new LinkedHashSet<SyntacticAnalyzer>();

	@Override
	public Set<SyntacticAnalysis> analyze(Sentence sentence) throws JTalkerException {
		Set<SyntacticAnalysis> result = null;
		for (SyntacticAnalyzer analyzer : analyzers) {
			result = analyzer.analyze(sentence);
			if (result != null) {
				return result;
			}
		}
		return null;
	}
	
	public void setAnalyzers(List<SyntacticAnalyzer> analyzers) {
		this.analyzers.addAll(analyzers);
	}
}
