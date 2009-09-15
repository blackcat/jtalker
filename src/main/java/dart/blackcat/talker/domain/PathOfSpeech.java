package dart.blackcat.talker.domain;

import dart.blackcat.talker.predicate.Predicate;

public enum PathOfSpeech {
	noun(null),								// существительное
	adjective(null), shortAdjective(null),		// прилагательное, краткое прилагательное
	verb(null), infinitive(null),				// глагол, инфинитив (глагола)
	participle(null), shortParticiple(null),	// причастие, краткое причастие
	gerund(null),							// деепричастие
	pronoun(null), pronounPredicative(null), pronounAdjective(null), // местоимение, местоимение-предикатив, местоимение прилагательное
	numeral(null), ordinal(null),				// числительное (количественное), порядковое числительное
	adverb(null),							// наречие
	predicateNoun(null),					// предикатив
	preposition(null),						// предлог
	conjunction(null),						// союз
	interjection(null),						// междометие
	particle(null),							// частица
	introduction(null),						// вводное слово
	phraseology(null);						// фразеологизм, фразеология
	
	PathOfSpeech(Predicate p) {};
}
