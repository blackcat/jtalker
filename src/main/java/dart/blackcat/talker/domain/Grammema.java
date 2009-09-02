package dart.blackcat.talker.domain;

public enum Grammema {
	animante,					// одушевленный
	inanimate,					// неодушевленный
	m, f, n, mf,				// муж., жен., средн., мужской-женский род
	singular,					// единственное число
	plural,						// множественное число
	nominative,					// именительный
	genitive,					// родительный
	accusative,					// винительный
	dative,						// дательный
	instrumental,				// творительный
	prepositional,				// предложный
	vocative,					// звательный
	secondCase,					// обозначает второй родительный или второй предложный падежи
	perfectForm, imperfectForm,	// совершенный вид, несовершенный вид
	transitiveVerb, nonTransitiveVerb, // переходный, непереходный глагол
	activeVoice, passiveVoice, 	// действительный, страдательный залог
	present, past, future,		// настоящее, прошедшее, будущее время
	imperativeMood,				// повелительная форма глагола
	firstPerson, secondPerson, thirdPerson,	// первое, второе, третье лицо
	invariable,					// неизменяемое
	brevity,					// краткость (для прилагательных и причастий)
	comparative,				// сравнительная форма (для прилагательных)
	name, surname, patronymic,	// имя, фамилия, отчество
	locativity, organization,	// локативность, организация
	quality,					// качественное прилагательное
	interrogative, relativity,	// вопросительность и относительность (для наречий)
	dfst, 						// слово обычно не имеет множественного числа
	typo, 						// частая опечатка или ошибка
	slang, archaism, professionalism, // жаргонизм, архаизм, профессионализм
	acronym,					// аббревиатура
	impersonal,					// безличный глагол
	superlative,				// превосходная степень 
	informal,					// разговорный
	indicating,					// указательное (наречие)
	possessive,					// притяжательное (местоимение, прилагательное)
}
