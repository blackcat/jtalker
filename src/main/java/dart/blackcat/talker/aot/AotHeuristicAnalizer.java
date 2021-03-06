package dart.blackcat.talker.aot;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.Assert;

import dart.blackcat.talker.aot.dao.AbstractAotDao;
import dart.blackcat.talker.morph.MorphologyAnalysis;
import dart.blackcat.talker.morph.MorphologyAnalyzer;

public class AotHeuristicAnalizer implements MorphologyAnalyzer {
	
	@SuppressWarnings("unused")
	private AotMorphologyAnalyzer morphologyAnalyzer;
	private AbstractAotDao aotDao;
	@SuppressWarnings("unused")
	private int limit = 5;

	@Override
	public Set<MorphologyAnalysis> analyze(String word) throws AotException {
		word = word.toUpperCase();
		Set<MorphologyAnalysis> result = new HashSet<MorphologyAnalysis>();
/*		for (int i = 1; i < limit; i++) {
			result = morphologyAnalyzer.analyze(word.substring(i));
			if ( ! result.isEmpty()) {
				return result;
			}
		}*/
		
		for (int i = 1; i < word.length(); i++) {
			result = aotDao.findWordByFlexia(word.substring(0, i), word.substring(i));
		}
		
		return result;
	}

	/**
	 * required.
	 * @param morphologyAnalyzer
	 */
	@Required
	public void setMorphologyAnalyzer(AotMorphologyAnalyzer morphologyAnalyzer) {
		this.morphologyAnalyzer = morphologyAnalyzer;
	}

	/**
	 * required. 
	 * @param aotDao
	 */
	@Required
	public void setAotDao(AbstractAotDao aotDao) {
		this.aotDao = aotDao;
	}

	/**
	 * Set limit for unknown prefix length. Default 5.
	 */
	public void setLimit(int limit) {
		Assert.isTrue(limit > 0);
		this.limit = limit;
	}

}
