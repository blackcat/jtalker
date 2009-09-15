package dart.blackcat.talker.domain;

import java.io.InputStreamReader;

import org.springframework.beans.factory.annotation.Required;

public class TextAnalyzer {
	
	private MorphologyAnalyzer morphologyAnalyzer;

	public void analyze(InputStreamReader reader) {
		
	}

	@Required
	public void setMorphologyAnalyzer(MorphologyAnalyzer morphologyAnalyzer) {
		this.morphologyAnalyzer = morphologyAnalyzer;
	}
}
