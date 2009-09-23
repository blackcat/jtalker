package dart.blackcat.talker.domain;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import dart.blackcat.talker.morph.MorphologyAnalysis;

public class Word implements Serializable {

	private static final long serialVersionUID = 8599848825365060401L;

	
	private String callback;
	private Set<MorphologyAnalysis> morphologyAnalysisSet;
	
	public Word(String word) {
		callback = word;
	}
	
	public String getWord() {
		return callback;
	}

	public Set<MorphologyAnalysis> getMorphologyAnalysisSet() {
		return morphologyAnalysisSet;
	}

	public void setMorphologyAnalysisSet(
			Set<MorphologyAnalysis> morphologyAnalysisSet) {
		this.morphologyAnalysisSet = morphologyAnalysisSet;
	}
	
	public boolean hasGrammema(long grammema) {
		for (Iterator<MorphologyAnalysis> i = morphologyAnalysisSet.iterator(); i.hasNext();) {
			MorphologyAnalysis analysis = i.next();
			if ( (analysis.getGrammemas() & grammema) == grammema) {
				return true;
			}
		}
		return false;
	}
}
