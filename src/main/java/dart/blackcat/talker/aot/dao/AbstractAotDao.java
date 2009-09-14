package dart.blackcat.talker.aot.dao;

import java.util.Set;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dart.blackcat.talker.aot.AotException;
import dart.blackcat.talker.domain.Grammema;
import dart.blackcat.talker.domain.MorphologyAnalysis;
import dart.blackcat.talker.domain.PathOfSpeech;


public abstract class AbstractAotDao extends JdbcDaoSupport {

	public abstract Set<MorphologyAnalysis> findWord(final String lemma, final String flexia) throws AotException;
	
	public abstract Set<String> getPrefixes();
	
	protected PathOfSpeech string2PathOfSpeech(String s) throws DatabaseIntegrityViolationException {
		if (s.equals("П")) {
			return PathOfSpeech.adjective;
		} else if (s.equals("Н")) {
			return PathOfSpeech.adverb;
		} else if (s.equals("СОЮЗ")) {
			return PathOfSpeech.conjunction;
		} else if (s.equals("ДЕЕПРИЧАСТИЕ")) {
			return PathOfSpeech.gerund;
		} else if (s.equals("ИНФИНИТИВ")) {
			return PathOfSpeech.infinitive;
		} else if (s.equals("МЕЖД")) {
			return PathOfSpeech.interjection;
		} else if (s.equals("ВВОДН")) {
			return PathOfSpeech.introduction;
		} else if (s.equals("С")) {
			return PathOfSpeech.noun;
		} else if (s.equals("ЧИСЛ")) {
			return PathOfSpeech.numeral;
		} else if (s.equals("ЧИСЛ-П")) {
			return PathOfSpeech.ordinal;
		} else if (s.equals("ПРИЧАСТИЕ")) {
			return PathOfSpeech.participle;
		} else if (s.equals("ЧАСТ")) {
			return PathOfSpeech.particle;
		} else if (s.equals("ФРАЗ")) {
			return PathOfSpeech.phraseology;
		} else if (s.equals("ПРЕДК")) {
			return PathOfSpeech.predicateNoun;
		} else if (s.equals("ПРЕДЛ")) {
			return PathOfSpeech.preposition;
		} else if (s.equals("МС")) {
			return PathOfSpeech.pronoun;
		} else if (s.equals("МС-П")) {
			return PathOfSpeech.pronounAdjective;
		} else if (s.equals("МС-ПРЕДК")) {
			return PathOfSpeech.pronounPredicative;
		} else if (s.equals("КР_ПРИЛ")) {
			return PathOfSpeech.shortAdjective;
		} else if (s.equals("КР_ПРИЧАСТИЕ")) {
			return PathOfSpeech.shortParticiple;
		} else if (s.equals("Г")) {
			return PathOfSpeech.verb;
		} else {
			throw new DatabaseIntegrityViolationException("Unknown path of speech: " + s);
		}
	}

	
	protected Grammema string2Grammema(String s) throws DatabaseIntegrityViolationException {
		if (s.equals("вн")) {
			return Grammema.accusative;
		} else if (s.equals("аббр")) {
			return Grammema.acronym;
		} else if (s.equals("дст")) {
			return Grammema.activeVoice;
		} else if (s.equals("од")) {
			return Grammema.animante;
		} else if (s.equals("арх")) {
			return Grammema.archaism;
		} else if (s.equals("кр")) {
			return Grammema.brevity;
		} else if (s.equals("сравн")) {
			return Grammema.comparative;
		} else if (s.equals("дт")) {
			return Grammema.dative;
		} else if (s.equals("дфст")) {
			return Grammema.dfst;
		} else if (s.equals("жр")) {
			return Grammema.f;
		} else if (s.equals("1л")) {
			return Grammema.firstPerson;
		} else if (s.equals("буд")) {
			return Grammema.future;
		} else if (s.equals("рд")) {
			return Grammema.genitive;
		} else if (s.equals("пвл")) {
			return Grammema.imperativeMood;
		} else if (s.equals("нс")) {
			return Grammema.imperfectForm;
		} else if (s.equals("безл")) {
			return Grammema.impersonal;
		} else if (s.equals("но")) {
			return Grammema.inanimate;
		} else if (s.equals("разг")) {
			return Grammema.informal;
		} else if (s.equals("указат")) {
			return Grammema.indicating;
		} else if (s.equals("тв")) {
			return Grammema.instrumental;
		} else if (s.equals("вопр")) {
			return Grammema.interrogative;
		} else if (s.equals("0")) {
			return Grammema.invariable;
		} else if (s.equals("лок")) {
			return Grammema.locativity;
		} else if (s.equals("мр")) {
			return Grammema.m;
		} else if (s.equals("мр-жр")) {
			return Grammema.mf;
		} else if (s.equals("ср")) {
			return Grammema.n;
		} else if (s.equals("имя")) {
			return Grammema.name;
		} else if (s.equals("им")) {
			return Grammema.nominative;
		} else if (s.equals("нп")) {
			return Grammema.nonTransitiveVerb;
		} else if (s.equals("орг")) {
			return Grammema.organization;
		} else if (s.equals("стр")) {
			return Grammema.passiveVoice;
		} else if (s.equals("прш")) {
			return Grammema.past;
		} else if (s.equals("отч")) {
			return Grammema.patronymic;
		} else if (s.equals("св")) {
			return Grammema.perfectForm;
		} else if (s.equals("мн")) {
			return Grammema.plural;
		} else if (s.equals("притяж")) {
			return Grammema.possessive;
		} else if (s.equals("пр")) {
			return Grammema.prepositional;
		} else if (s.equals("нст")) {
			return Grammema.present;
		} else if (s.equals("проф")) {
			return Grammema.professionalism;
		} else if (s.equals("кач")) {
			return Grammema.quality;
		} else if (s.equals("относ")) {
			return Grammema.relativity;
		} else if (s.equals("2")) {
			return Grammema.secondCase;
		} else if (s.equals("2л")) {
			return Grammema.secondPerson;
		} else if (s.equals("ед")) {
			return Grammema.singular;
		} else if (s.equals("жарг")) {
			return Grammema.slang;
		} else if (s.equals("прев")) {
			return Grammema.superlative;
		} else if (s.equals("фам")) {
			return Grammema.surname;
		} else if (s.equals("3л")) {
			return Grammema.thirdPerson;
		} else if (s.equals("пе")) {
			return Grammema.transitiveVerb;
		} else if (s.equals("опч")) {
			return Grammema.typo;
		} else if (s.equals("зв")) {
			return Grammema.vocative;
		} else {
			throw new DatabaseIntegrityViolationException("Unknown grammema: " + s);
		}
	}
}
