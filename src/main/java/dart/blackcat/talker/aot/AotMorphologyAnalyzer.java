package dart.blackcat.talker.aot;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

import dart.blackcat.talker.aot.dao.AbstractAotDao;
import dart.blackcat.talker.aot.dao.AotDBDao;
import dart.blackcat.talker.morph.MorphologyAnalysis;
import dart.blackcat.talker.morph.MorphologyAnalyzer;
import dart.blackcat.talker.util.StringLengthComparator;

/**
 * {@link MorphologyAnalysis} factory
 * @author pvyazankin
 *
 */
public class AotMorphologyAnalyzer implements MorphologyAnalyzer {
	
	private static final Log log = LogFactory.getLog(AotMorphologyAnalyzer.class);
	
	private AbstractAotDao aotDao;
	private SortedSet<String> prefixes = new TreeSet<String>(new StringLengthComparator(false));

	/**
	 * required
	 * @param aotDao
	 */
	@Required
	public void setAotDao(AotDBDao aotDao) {
		this.aotDao = aotDao;
	}
	
	public void init() {
		prefixes.addAll(aotDao.getPrefixes());
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
		

		if ( ! word.isEmpty() && ! word.replace("-", "").isEmpty()) {
			result = analyze0(word);
			
			if (result.isEmpty()) {
				String wordWithOutPrefix = cutPrefix(word);
				
				if (wordWithOutPrefix != null) {
					result = analyze0(wordWithOutPrefix);
				}
			}
			
			if (result.isEmpty() && word.contains("-")) {
				StringTokenizer st1 = new StringTokenizer(word, "-");
				String token = null;
				while (st1.hasMoreTokens()) {
					token = st1.nextToken();
				}
				result = analyze0(token);
			}
		}
		
		log.debug(word + " " + result.size() + " results found.");
		return result;
	}
	
	protected Set<MorphologyAnalysis> analyze0(String word) throws AotException {
		Set<MorphologyAnalysis> result = new HashSet<MorphologyAnalysis>();
		int length = word.length();

		while (/*result.isEmpty() && */length >= 0) {
			result.addAll(
					aotDao.findWord(word.substring(0, length), word.substring(length))
			);
			length--;
		}
		
		return result;
	}
	
	/**
	 * cut prefix or return null if unable
	 */
	protected String cutPrefix(String word) {
		for (Iterator<String> i = prefixes.iterator(); i.hasNext();) {
			String prefix = i.next();
			if (word.startsWith(prefix)) {
				return word.substring(prefix.length());
			}
		}
		return null;
	}
	
}
