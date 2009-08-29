package dart.blackcat.talker.aot;

import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dart.blackcat.talker.aot.dao.AotDao;

/**
 * {@link MorphologyAnalysis} factory
 * @author pvyazankin
 *
 */
public class AotMorphologyAnalyzer {
	
	private static final Log log = LogFactory.getLog(AotMorphologyAnalyzer.class);
	
	private AotDao aotDao;

	public void setAotDao(AotDao aotDao) {
		this.aotDao = aotDao;
	}

	/**
	 * {@link MorphologyAnalysis} factory method
	 * @param word word to analyze
	 * @return {@link Set} of {@link MorphologyAnalysis}es
	 * @throws UnableToAnalyzeException when can't analyze
	 */
	public Set<MorphologyAnalysis> analyze(String word) throws UnableToAnalyzeException {
		word = word.toUpperCase();
		log.debug(word + " - analyzing");
		Set<MorphologyAnalysis> result = null;
		int length = word.length();

		result = aotDao.findWord(word, "");
		while (result.isEmpty() && length != 0) {
			length--;
			result = aotDao.findWord(word.substring(0, length), word.substring(length));
		}
		
		if (length == 0) {
			log.debug(word + " unable to analyze.");
			throw new UnableToAnalyzeException(word);
		}
		
		log.debug(word + " " + result.size() + " results found.");
		return result;
	}
}
