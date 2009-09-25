package dart.blackcat.talker.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import dart.blackcat.talker.morph.Grammema;
import dart.blackcat.talker.morph.MorphologyAnalysis;
import dart.blackcat.talker.morph.PathOfSpeech;

public class Word implements Serializable {

	private static final long serialVersionUID = 8599848825365060401L;

	
	private String callback;
	private Map<MorphologyAnalysis, Integer> morphologyAnalysisMap = new HashMap<MorphologyAnalysis, Integer>();
	
	public Word(String word) {
		callback = word;
	}
	
	public String getWord() {
		return callback;
	}

	public Set<MorphologyAnalysis> getMorphologyAnalysisSet() {
		return morphologyAnalysisMap.keySet();
	}

	public void setMorphologyAnalysisSet(Set<MorphologyAnalysis> morphologyAnalysisSet) {
		for (Iterator<MorphologyAnalysis> i = morphologyAnalysisSet.iterator(); i.hasNext();) {
			morphologyAnalysisMap.put(i.next(), 0);			
		}
	}
	
	/**
	 * Check if one of morphology analyses has required grammema. 
	 * @param grammema
	 * @param critical if critical, then found analysis will have more balls.
	 * @return
	 */
	public boolean hasGrammema(boolean critical, Grammema... grammema) {
		boolean result = false;
		for (Iterator<Entry<MorphologyAnalysis, Integer>> i = morphologyAnalysisMap.entrySet().iterator(); i.hasNext();) {
			Entry<MorphologyAnalysis, Integer> entry = i.next();

			if (entry.getKey().hasGrammema(grammema)) {
				if (critical) {
					entry.setValue(entry.getValue() + 1);	
				}
				result = true;
			}
		}
		return result;
	}
	
	/**
	 * Check if one of morphology analysis has required path of speech. 
	 * @param pathOfSpeech
	 * @param critical if critical, then found analysis will have more balls.
	 * @return
	 */
	public boolean hasPathOfSpeech(boolean critical, PathOfSpeech... pathOfSpeech) {
		boolean result = false;
		for (Iterator<Entry<MorphologyAnalysis, Integer>> i = morphologyAnalysisMap.entrySet().iterator(); i.hasNext();) {
			Entry<MorphologyAnalysis, Integer> entry = i.next();

			if (entry.getKey().hasPathOfSpeech(pathOfSpeech)) {
				if (critical) {
					entry.setValue(entry.getValue() + 1);	
				}
				result = true;
			}
		}
		return result;
	}
	
	public MorphologyAnalysis getBestMorphologyAnalysis() {
		MorphologyAnalysis result = null;
		Integer k = 0;
		
		for (Iterator<Entry<MorphologyAnalysis, Integer>> i = morphologyAnalysisMap.entrySet().iterator(); i.hasNext();) {
			Entry<MorphologyAnalysis, Integer> entry = i.next();
			
			if (k < entry.getValue() || result == null) {
				result = entry.getKey();
				k = entry.getValue();
			}
		}

		return result;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("Word:");
		sb.append(callback);
		sb.append(morphologyAnalysisMap);
		return sb.toString();
	}
}
