package dart.blackcat.talker.aot.dao;

import java.util.Set;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

import dart.blackcat.talker.aot.AotException;
import dart.blackcat.talker.morph.Grammema;
import dart.blackcat.talker.morph.Grammemas;
import dart.blackcat.talker.morph.MorphologyAnalysis;
import dart.blackcat.talker.morph.PathOfSpeech;


public abstract class AbstractAotDao extends JdbcDaoSupport {

	public abstract Set<MorphologyAnalysis> findWord(final String lemma, final String flexia) throws AotException;
	
	public abstract Set<String> getPrefixes();
	
	public abstract Set<MorphologyAnalysis> findWordByFlexia(String lemma, final String flexia) throws AotException;
	
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

	
	protected long string2Grammema(String s) throws DatabaseIntegrityViolationException {
		if (s.equals("вн")) {
			return Grammemas.accusative;
		} else if (s.equals("аббр")) {
			return Grammemas.acronym;
		} else if (s.equals("дст")) {
			return Grammemas.activeVoice;
		} else if (s.equals("од")) {
			return Grammemas.animante;
		} else if (s.equals("арх")) {
			return Grammemas.archaism;
		} else if (s.equals("кр")) {
			return Grammemas.brevity;
		} else if (s.equals("сравн")) {
			return Grammemas.comparative;
		} else if (s.equals("дт")) {
			return Grammemas.dative;
		} else if (s.equals("дфст")) {
			return Grammemas.dfst;
		} else if (s.equals("жр")) {
			return Grammemas.f;
		} else if (s.equals("1л")) {
			return Grammemas.firstPerson;
		} else if (s.equals("буд")) {
			return Grammemas.future;
		} else if (s.equals("рд")) {
			return Grammemas.genitive;
		} else if (s.equals("пвл")) {
			return Grammemas.imperativeMood;
		} else if (s.equals("нс")) {
			return Grammemas.imperfectForm;
		} else if (s.equals("безл")) {
			return Grammemas.impersonal;
		} else if (s.equals("но")) {
			return Grammemas.inanimate;
		} else if (s.equals("разг")) {
			return Grammemas.informal;
		} else if (s.equals("указат")) {
			return Grammemas.indicating;
		} else if (s.equals("тв")) {
			return Grammemas.instrumental;
		} else if (s.equals("вопр")) {
			return Grammemas.interrogative;
		} else if (s.equals("0")) {
			return Grammemas.invariable;
		} else if (s.equals("лок")) {
			return Grammemas.locativity;
		} else if (s.equals("мр")) {
			return Grammemas.m;
		} else if (s.equals("мр-жр")) {
			return Grammemas.mf;
		} else if (s.equals("ср")) {
			return Grammemas.n;
		} else if (s.equals("имя")) {
			return Grammemas.name;
		} else if (s.equals("им")) {
			return Grammemas.nominative;
		} else if (s.equals("нп")) {
			return Grammemas.nonTransitiveVerb;
		} else if (s.equals("орг")) {
			return Grammemas.organization;
		} else if (s.equals("стр")) {
			return Grammemas.passiveVoice;
		} else if (s.equals("прш")) {
			return Grammemas.past;
		} else if (s.equals("отч")) {
			return Grammemas.patronymic;
		} else if (s.equals("св")) {
			return Grammemas.perfectForm;
		} else if (s.equals("мн")) {
			return Grammemas.plural;
		} else if (s.equals("притяж")) {
			return Grammemas.possessive;
		} else if (s.equals("пр")) {
			return Grammemas.prepositional;
		} else if (s.equals("нст")) {
			return Grammemas.present;
		} else if (s.equals("проф")) {
			return Grammemas.professionalism;
		} else if (s.equals("кач")) {
			return Grammemas.quality;
		} else if (s.equals("относ")) {
			return Grammemas.relativity;
		} else if (s.equals("2")) {
			return Grammemas.secondCase;
		} else if (s.equals("2л")) {
			return Grammemas.secondPerson;
		} else if (s.equals("ед")) {
			return Grammemas.singular;
		} else if (s.equals("жарг")) {
			return Grammemas.slang;
		} else if (s.equals("прев")) {
			return Grammemas.superlative;
		} else if (s.equals("фам")) {
			return Grammemas.surname;
		} else if (s.equals("3л")) {
			return Grammemas.thirdPerson;
		} else if (s.equals("пе")) {
			return Grammemas.transitiveVerb;
		} else if (s.equals("опч")) {
			return Grammemas.typo;
		} else if (s.equals("зв")) {
			return Grammemas.vocative;
		} else {
			throw new DatabaseIntegrityViolationException("Unknown Grammema: " + s);
		}
	}
}
