package dart.blackcat.talker.aot;

/**
 * {@link MorphologyAnalysis} factory
 * @author pvyazankin
 *
 */
public class MorphologyAnalyzer {

	public MorphologyAnalysis analyze(String word) {
		
		return new MorphologyAnalysis(prefix, base, flexia, pathOfSpeech, grammemas);
	}
}
