package dart.blackcat.talker.aot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Required;

import dart.blackcat.talker.JTalkerException;
import dart.blackcat.talker.domain.Sentence;
import dart.blackcat.talker.domain.Word;
import dart.blackcat.talker.morph.Grammemas;
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
		
		Word subject;
		Word predicate;
		Set<SyntacticAnalysis> result = new HashSet<SyntacticAnalysis>();
		
		// morphology analysis
		for (Word word : sentence) {
			word.setMorphologyAnalysisSet(
					morphologyAnalyzer.analyze(word.getWord())
			);
		}
		
		Set<Word> potentialSubjectSet = new HashSet<Word>();
		for (Iterator<Word> i = sentence.iterator(); i.hasNext();) {
			Word word = i.next();
			if (canBeSubject(word)) {
				potentialSubjectSet.add(word);
			}
		}
		if (potentialSubjectSet.size() == 1) {
			subject = potentialSubjectSet.iterator().next();
			
			// у подлежащего и сказуемого должны совпадать лицо и род
			long grammemaFilter = Grammemas.singular | Grammemas.plural | Grammemas.firstPerson | Grammemas.secondPerson | Grammemas.thirdPerson;
			
			for (Word word : sentence) {
				if (
						word.hasPathOfSpeech(PathOfSpeech.verb, true) &&
						word.hasGrammema(
								subject.getBestMorphologyAnalysis().getGrammemas() & grammemaFilter,
								false
						)
				) {
					predicate = word;
					result.add(new SyntacticAnalysis(subject, predicate, sentence));
				}
			}
		}
		
		
		return result;
	}
	
	public boolean canBeSubject(Word word) {
		return 
			word.hasGrammema(Grammemas.nominative, true) && (
					word.hasPathOfSpeech(PathOfSpeech.noun, true) || word.hasPathOfSpeech(PathOfSpeech.pronoun, true)
			);
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
		LinkedList<Word> result = new LinkedList<Word>();
		
		for (Iterator<Word> i = sentence.iterator(); i.hasNext();) {
			Word word = i.next();
			
			if (word.hasPathOfSpeech(PathOfSpeech.noun, true) && word.hasGrammema(Grammemas.nominative, true)) {
				result.add(0, word);
			}
			if (word.hasPathOfSpeech(PathOfSpeech.pronoun, true)) {
				result.add(1, word);
			}
			if (word.hasPathOfSpeech(PathOfSpeech.numeral | PathOfSpeech.ordinal, true)) {
				result.add(2, word);
			}
			if (word.hasPathOfSpeech(PathOfSpeech.infinitive, true)) {
				result.add(3, word);
			}
			if (word.hasGrammema(Grammemas.name | Grammemas.surname | Grammemas.patronymic, true)) {
				result.add(4, word);
			}
		}
		
		return result;
	}
	
	protected Set<Word> findPredicate(Sentence sentence, Word subject) {
		for (Word word : sentence) {
			
			for (Iterator<MorphologyAnalysis> i = word.getMorphologyAnalysisSet().iterator(); i.hasNext();) {
				MorphologyAnalysis analysis = i.next();
				
				if (
						analysis.hasGrammema(
							subject.getBestMorphologyAnalysis().getGrammemas() &
							(
								Grammemas.singular | 
								Grammemas.plural | 
								Grammemas.firstPerson | 
								Grammemas.secondPerson | 
								Grammemas.thirdPerson
							)
						)
				) {
					
				}
		}
	}

	@Required
	public void setMorphologyAnalyzer(MorphologyAnalyzer analyzer) {
		morphologyAnalyzer = analyzer;
	}
}
