package dart.blackcat.talker.domain;

import java.io.Serializable;

import dart.blackcat.talker.morph.MorphologyAnalysis;

public class Word implements Serializable {

	private static final long serialVersionUID = 8599848825365060401L;

	
	private String callback;
	private MorphologyAnalysis morphologyAnalysis;
	
	public Word(String word) {
		callback = word;
	}
	
	public String getWord() {
		return callback;
	}
	
	public MorphologyAnalysis getMorphologyAnalysis() {
		return morphologyAnalysis;
	}
	
	public void setMorphologyAnalysis(MorphologyAnalysis morphologyAnalysis) {
		this.morphologyAnalysis = morphologyAnalysis;
	}
}
