package dart.blackcat.talker.domain;


public enum PathOfSpeech {
	noun,								// существительное
	adjective, shortAdjective,		// прилагательное, краткое прилагательное
	verb, infinitive,				// глагол, инфинитив (глагола)
	participle, shortParticiple,	// причастие, краткое причастие
	gerund,							// деепричастие
	pronoun, pronounPredicative, pronounAdjective, // местоимение, местоимение-предикатив, местоимение прилагательное
	numeral, ordinal,				// числительное (количественное), порядковое числительное
	adverb,							// наречие
	predicateNoun,					// предикатив
	preposition,						// предлог
	conjunction,						// союз
	interjection,						// междометие
	particle,							// частица
	introduction,						// вводное слово
	phraseology;						// фразеологизм, фразеология
}
