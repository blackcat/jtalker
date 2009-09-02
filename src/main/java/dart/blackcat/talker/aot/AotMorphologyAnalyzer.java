package dart.blackcat.talker.aot;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dart.blackcat.talker.aot.dao.AotDao;
import dart.blackcat.talker.domain.MorphologyAnalysis;
import dart.blackcat.talker.domain.MorphologyAnalyzer;

/**
 * {@link MorphologyAnalysis} factory
 * @author pvyazankin
 *
 */
public class AotMorphologyAnalyzer implements MorphologyAnalyzer {
	
	private static final Log log = LogFactory.getLog(AotMorphologyAnalyzer.class);
	
	private AotDao aotDao;

	public void setAotDao(AotDao aotDao) {
		this.aotDao = aotDao;
	}

	/**
	 * {@link MorphologyAnalysis} factory method
	 * @param word word to analyze
	 * @return {@link Set} of {@link MorphologyAnalysis}es. Can be empty.
	 * @throws AotException if there is any problem
	 */
	@Override
	public Set<MorphologyAnalysis> analyze(String word) throws AotException  {
		word = word.toUpperCase();
		log.debug(word + " - analyzing");
		Set<MorphologyAnalysis> result = new HashSet<MorphologyAnalysis>();
		int length = word.length();

		while (result.isEmpty() && length >= 0) {
			result = aotDao.findWord(word.substring(0, length), word.substring(length));
			length--;
		}
		
		if (length == 0) {
			log.debug(word + " unable to analyze.");
			return result;
		}
		
		log.debug(word + " " + result.size() + " results found.");
		return result;
	}
}
