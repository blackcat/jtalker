package dart.blackcat.talker.aot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import dart.blackcat.talker.JTalkerException;
import dart.blackcat.talker.domain.Sentence;
import dart.blackcat.talker.domain.Word;
import dart.blackcat.talker.morph.MorphologyAnalysis;
import dart.blackcat.talker.morph.MorphologyAnalyzer;
import dart.blackcat.talker.syntax.SyntacticAnalysis;
import dart.blackcat.talker.syntax.SyntacticAnalyzer;

public class AotSyntacticAnalyzer implements SyntacticAnalyzer {
	
	private MorphologyAnalyzer morphologyAnalyzer;

	@Override
	public Set<SyntacticAnalysis> analyze(Sentence sentence) throws JTalkerException {
		
		List<Word> wordList = new ArrayList<Word>();
		
		// morphology analysis
		for (String wordString : sentence) {
			Word word = new Word(wordString);
			word.setMorphologyAnalysisSet(
					morphologyAnalyzer.analyze(wordString)
			);
			wordList.add(word);
		}
		
		for (Iterator<Word> i = wordList.iterator(); i.hasNext();) {
			Word word = i.next();
			if (canBeSubject(word)) {
				//TODO
			}
		}
		
		
		return null;
	}
	
	public boolean canBeSubject(Word word) {
		for (Iterator<MorphologyAnalysis> i = word.getMorphologyAnalysisSet().iterator(); i.hasNext();) {
			MorphologyAnalysis morphologyAnalysis = i.next();
			
		}
		return false;
	}

	
	public void setMorphologyAnalyzer(MorphologyAnalyzer analyzer) {
		morphologyAnalyzer = analyzer;
	}
}
