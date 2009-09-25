package dart.blackcat.talker.morph;

import java.util.ArrayList;
import java.util.List;

public enum Grammema {
	
	/**
	 * одушевленный
	 */
	animate(0x1L, "одушевленный"), 

	
	/**
	 * неодушевленный
	 */
	inanimate(0x2L, "неодушевленный"), 

	
	/**
	 * мужской род
	 */
	m(0x4L, "мужской род"), 

	
	/**
	 * женский род
	 */
	f(0x8L, "женский род"), 

	
	/**
	 * средний род
	 */
	n(0x10L, "средний род"), 

	
	/**
	 * мужской-женский род
	 */
	mf(m.code | f.code, "мужской-женский род"),

	
	/**
	 * единственное число
	 */
	singular(0x20L, "единственное число"),

	
	/**
	 * множественное число
	 */
	plural(0x40L, "множественное число"),

	
	/**
	 * именительный
	 */
	nominative(0x80L, "именительный"),

	
	/**
	 * родительный
	 */
	genitive(0x100L, "родительный"),

	
	/**
	 * винительный
	 */
	accusative(0x200L, "винительный"),

	
	/**
	 * дательный
	 */
	dative(0x400L, "дательный"),

	
	/**
	 * творительный
	 */
	instrumental(0x800L, "творительный"),

	
	/**
	 * предложный
	 */
	prepositional(0x1000L, "предложный"),

	
	/**
	 * звательный
	 */
	vocative(0x2000L, "звательный"),

	
	/**
	 * обозначает второй родительный или второй предложный падежи
	 */
	secondCase(0x4000L, "второй родительный или второй предложный"),

	
	/**
	 * совершенный вид
	 */
	perfectForm(0x8000L, "совершенный вид"), 

	
	/**
	 * несовершенный вид
	 */
	imperfectForm(0x10000L, "несовершенный вид"),

	
	/**
	 * переходный
	 */
	transitiveVerb(0x20000L, "переходный"), 

	
	/**
	 * непереходный глагол
	 */
	nonTransitiveVerb(0x40000L, "непереходный глагол"),

	
	/**
	 * действительный
	 */
	activeVoice(0x80000L, "действительный"), 

	
	/**
	 * страдательный залог
	 */
	passiveVoice(0x100000L, "страдательный залог"),

	
	/**
	 * настоящее
	 */
	present(0x200000L, "настоящее"), 

	
	/**
	 * прошедшее
	 */
	past(0x400000L, "прошедшее"), 

	
	/**
	 * будущее время
	 */
	future(0x800000L, "будущее время"),

	
	/**
	 * повелительная форма глагола
	 */
	imperativeMood(0x1000000L, "повелительная форма глагола"),

	
	/**
	 * первое лицо
	 */
	firstPerson(0x2000000L, "первое лицо"), 

	
	/**
	 * второе лицо
	 */
	secondPerson(0x4000000L, "второе лицо"), 

	
	/**
	 * третье лицо
	 */
	thirdPerson(0x8000000L, "третье лицо"),

	
	/**
	 * неизменяемое
	 */
	invariable(0x10000000L, "неизменяемое"),

	
	/**
	 * краткость (для прилагательных и причастий)
	 */
	brevity(0x20000000L, "краткость"),

	
	/**
	 * сравнительная форма (для прилагательных)
	 */
	comparative(0x40000000L, "сравнительная форма"),

	
	/**
	 * имя
	 */
	name(0x80000000L, "имя"), 

	
	/**
	 * фамилия
	 */
	surname(0x100000000L, "фамилия"), 

	
	/**
	 * отчество
	 */
	patronymic(0x200000000L, "отчество"),

	
	/**
	 * локативность
	 */
	locativity(0x400000000L, "локативность"), 

	
	/**
	 * организация
	 */
	organization(0x800000000L, "организация"),

	
	/**
	 * качественное прилагательное
	 */
	quality(0x1000000000L, "качественное прилагательное"),

	
	/**
	 * вопросительность (для наречий)
	 */
	interrogative(0x2000000000L, "вопросительность"), 

	
	/**
	 * относительность (для наречий)
	 */
	relativity(0x4000000000L, "относительность"),

	
	/**
	 * слово обычно не имеет множественного числа
	 */
	dfst(0x8000000000L, "не имеет множественного числа"),

	
	/**
	 * частая опечатка или ошибка
	 */
	typo(0x10000000000L, "ошибка"),

	
	/**
	 * жаргонизм
	 */
	slang(0x20000000000L, "жаргонизм"), 

	
	/**
	 * архаизм
	 */
	archaism(0x40000000000L, "архаизм"), 

	
	/**
	 * профессионализм
	 */
	professionalism(0x80000000000L, "профессионализм"),

	
	/**
	 * аббревиатура
	 */
	acronym(0x100000000000L, "аббревиатура"),

	
	/**
	 * безличный глагол
	 */
	impersonal(0x200000000000L, "безличный глагол"),

	
	/**
	 * превосходная степень 
	 */
	superlative(0x400000000000L, "превосходная степень"),

	
	/**
	 * разговорный
	 */
	informal(0x800000000000L, "разговорный"),

	
	/**
	 * указательное (наречие)
	 */
	indicating(0x1000000000000L, "указательное"),

	
	/**
	 * притяжательное (местоимение, прилагательное)
	 */
	possessive(0x2000000000000L, "притяжательное"),

	;
	

	private final long code;
	private final String grammemaName;
	
	private Grammema(long constant, String name) {
		this.code = constant;
		grammemaName = name;
	}
	
	public long getCode() {
		return code;
	}
	
	public String getName() {
		return grammemaName;
	}
	
	@Override
	public String toString() {
		return grammemaName;
	}
	
	public static long getCumulativeGrammemaCode(Grammema... grammemas) {
		long result = 0L;
		
		for (int i = 0; i < grammemas.length; i++) {
			result |= grammemas[i].code;
		}
		
		return result;
	}
	
	public static String resolveCumulativeGrammemaName(long cumulativeGrammema) {
		StringBuffer result = new StringBuffer();
		
		for (Grammema grammema : Grammema.values()) {
			if ( (grammema.code & cumulativeGrammema) == grammema.code) {
				result.append(grammema.grammemaName).append(" ");
			}
		}
		
		return result.toString();
	}
	
	public static List<Grammema> resolveGrammemas(long cumulativeGrammema) {
		List<Grammema> result = new ArrayList<Grammema>();
		
		for (Grammema grammema : Grammema.values()) {
			if ( (grammema.code & cumulativeGrammema) == grammema.code) {
				result.add(grammema);
			}
		}
		
		return result;
	}
	
	public static boolean equals(long grammemaCode0, long grammemaCode1, Grammema... commonGrammemas) {
		long filter = Grammema.getCumulativeGrammemaCode(commonGrammemas);
		return (grammemaCode0 & filter) == (grammemaCode1 & filter);
	}
}
