package dart.blackcat.talker.aot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import dart.blackcat.talker.JTalkerException;
import dart.blackcat.talker.domain.Sentence;
import dart.blackcat.talker.domain.Word;
import dart.blackcat.talker.morph.Grammema;
import dart.blackcat.talker.morph.MorphologyAnalysis;
import dart.blackcat.talker.morph.MorphologyAnalyzer;
import dart.blackcat.talker.morph.PathOfSpeech;
import dart.blackcat.talker.syntax.SyntacticAnalysis;
import dart.blackcat.talker.syntax.SyntacticAnalyzer;

public class AotSyntacticAnalyzer implements SyntacticAnalyzer {
	
	private MorphologyAnalyzer morphologyAnalyzer;
	
	

	/**
	 * {@link SyntacticAnalysis} factory method.
	 * <br>
	 * <br>
	 * Краткое описание алгоритма: <br>
	 * Подлежащее выражается (в порядке вероятности):
	 * <ul>
	 * 		<li>существительным в именительном падеже</li>
	 * 		<li>личными местоимениями</li>
	 * 		<li>вопросительными местоимениями</li>
	 * 		<li>относительными местоимениям</li>
	 * 		<li>неопределенными местоимениями</li>
	 * 		<li>отрицательными местоимениями</li>
	 * 		<li>числительным</li>
	 * 		<li>неопределенной формой глагола (независимый инфинитив)</li>
	 * 		<li>именем собственным</li>
	 * </ul>
	 * @param sentence
	 * @return set of analyses, can be empty
	 * @throws JTalkerException in case errors
	 */
	@Override
	public Set<SyntacticAnalysis> analyze(Sentence sentence) throws JTalkerException {
		
		Set<SyntacticAnalysis> result = new HashSet<SyntacticAnalysis>();
		
		// morphology analysis
		for (Word word : sentence) {
			word.setMorphologyAnalysisSet(
					morphologyAnalyzer.analyze(word.getWord())
			);
		}
		
		List<Word> subjectList = analyzeForSubjects(sentence);
		for (Iterator<Word> i = subjectList.iterator(); i.hasNext();) {
			Word potentialSubject = i.next();
			Collection<Word> predicateCollection = findPredicate(sentence, potentialSubject);
			
			for (Iterator<Word> i0 = predicateCollection.iterator(); i0.hasNext();) {
				Word potentialPredicate = i0.next();
				result.add(new SyntacticAnalysis(potentialSubject, potentialPredicate, sentence));
			}
		}
		
		return result;
	}
	
	/**
	 * find potential subjects and save them into subjectProbabilisticList
	 * <br><br>
	 * Краткое описание алгоритма: <br>
	 * Подлежащее выражается (в порядке вероятности):
	 * <ol>
	 * 		<li>существительным в именительном падеже</li>
	 * 		<li>личным местоимением</li>
	 * 		<li>вопросительным местоимением</li>
	 * 		<li>относительным местоимением</li>
	 * 		<li>неопределенным местоимением</li>
	 * 		<li>отрицательным местоимением</li>
	 * 		<li>числительным</li>
	 * 		<li>неопределенной формой глагола (независимый инфинитив)</li>
	 * 		<li>именем собственным</li>
	 * </ol>
	 * 
	 * @param sentence
	 * @return {@link List} of {@link Word}s. First is the most probable subject.
	 */
	protected List<Word> analyzeForSubjects(Sentence sentence) {
		ArrayList<Word> result = new ArrayList<Word>(5);
		
		for (int i = 0; i < 5; i++) {
			result.add(null);
		}
		
		for (Iterator<Word> i = sentence.iterator(); i.hasNext();) {
			Word word = i.next();
			
			if (word.hasPathOfSpeech(true, PathOfSpeech.noun) && word.hasGrammema(true, Grammema.nominative)) {
				result.add(0, word);
			}
			if (word.hasPathOfSpeech(true, PathOfSpeech.pronoun)) {
				result.add(1, word);
			}
			if (word.hasPathOfSpeech(true, PathOfSpeech.numeral, PathOfSpeech.ordinal)) {
				result.add(2, word);
			}
			if (word.hasPathOfSpeech(true, PathOfSpeech.infinitive)) {
				result.add(3, word);
			}
			if (word.hasGrammema(true, Grammema.name, Grammema.surname, Grammema.patronymic)) {
				result.add(4, word);
			}
		}
		
		for (Iterator<Word> i = result.iterator(); i.hasNext();) {
			if (i.next() == null) {
				i.remove();
			}
		}
		
		return result;
	}
	
	protected Collection<Word> findPredicate(Sentence sentence, Word subject) {
		Collection<Word> result = new HashSet<Word>();
		
		for (Word word : sentence) {
			
			for (Iterator<MorphologyAnalysis> i = word.getMorphologyAnalysisSet().iterator(); i.hasNext();) {
				MorphologyAnalysis analysis = i.next();
				
				if (
						analysis.hasPathOfSpeech(PathOfSpeech.verb) &&
						Grammema.equals(
								subject.getBestMorphologyAnalysis().getGrammemas(), 
								analysis.getGrammemas(), 
								Grammema.singular,
								Grammema.plural,
								Grammema.firstPerson, 
								Grammema.secondPerson, 
								Grammema.thirdPerson
						)
				) {
					result.add(word);
				}
			}
		}
		
		return result;
	}

	@Required
	public void setMorphologyAnalyzer(MorphologyAnalyzer analyzer) {
		morphologyAnalyzer = analyzer;
	}
}
