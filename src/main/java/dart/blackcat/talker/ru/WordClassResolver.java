package dart.blackcat.talker.ru;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class WordClassResolver {
	
	private static final Log LOG = LogFactory.getLog(WordClassResolver.class);

	public WordClass resolve(String word) {
		if (Pronoun.isPronoun(word)) {
			return new Pronoun();
		} else if (Particle.isParticle(word)) {
			return new Particle();
		} else {
			MorphologicalAnalisys analisys = new MorphologicalAnalisys(word);
			LOG.info(analisys);
			return analisys.getWordClass();
		}
	}
}
