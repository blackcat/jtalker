package dart.blackcat.talker.morph;

import java.io.Serializable;

public interface Grammemas extends Serializable {
	/**
	 * одушевленный
	 */
	public static final long animante 			= 0x1L;						// 
	/**
	 * неодушевленный
	 */
	public static final long inanimate 			= 0x2L;						// 
	/**
	 * мужской род
	 */
	public static final long m 					= 0x4L;
	/**
	 * женский род
	 */
	public static final long f 					= 0x8L; 
	/**
	 * средний род
	 */
	public static final long n 					= 0x10L; 
	/**
	 * мужской-женский род
	 */
	public static final long mf = m | f;									// ., ., ., 
	/**
	 * единственное число
	 */
	public static final long singular 			= 0x20L;					// 
	/**
	 * множественное число
	 */
	public static final long plural 			= 0x40L;					// 
	/**
	 * именительный
	 */
	public static final long nominative 		= 0x80L;					// 
	/**
	 * родительный
	 */
	public static final long genitive 			= 0x100L;					// 
	/**
	 * винительный
	 */
	public static final long accusative 		= 0x200L;					// 
	/**
	 * дательный
	 */
	public static final long dative 			= 0x400L;					// 
	/**
	 * творительный
	 */
	public static final long instrumental 		= 0x800L;					// 
	/**
	 * предложный
	 */
	public static final long prepositional 		= 0x1000L;					// 
	/**
	 * звательный
	 */
	public static final long vocative 			= 0x2000L;					// 
	/**
	 * обозначает второй родительный или второй предложный падежи
	 */
	public static final long secondCase 		= 0x4000L;					// 
	/**
	 * совершенный вид
	 */
	public static final long perfectForm 		= 0x8000L; 
	/**
	 * несовершенный вид
	 */
	public static final long imperfectForm 		= 0x10000L;					// , 
	/**
	 * переходный
	 */
	public static final long transitiveVerb 	= 0x20000L; 	
	/**
	 * непереходный глагол
	 */
	public static final long nonTransitiveVerb	= 0x40000L; 				// , 
	/**
	 * действительный
	 */
	public static final long activeVoice		= 0x80000L; 
	/**
	 * страдательный залог
	 */
	public static final long passiveVoice		= 0x100000L; 				// , 
	/**
	 * настоящее
	 */
	public static final long present			= 0x200000L; 
	/**
	 * прошедшее
	 */
	public static final long past				= 0x400000L; 
	/**
	 * будущее время
	 */
	public static final long future				= 0x800000L;				// , , 
	/**
	 * повелительная форма глагола
	 */
	public static final long imperativeMood 	= 0x1000000L;				// 
	/**
	 * первое лицо
	 */
	public static final long firstPerson 		= 0x2000000L; 
	/**
	 * второе лицо
	 */
	public static final long secondPerson 		= 0x4000000L; 
	/**
	 * третье лицо
	 */
	public static final long thirdPerson 		= 0x8000000L;				// , , 
	/**
	 * неизменяемое
	 */
	public static final long invariable 		= 0x10000000L;				// 
	/**
	 * краткость (для прилагательных и причастий)
	 */
	public static final long brevity 			= 0x20000000L;				// 
	/**
	 * сравнительная форма (для прилагательных)
	 */
	public static final long comparative 		= 0x40000000L;				// 
	/**
	 * имя
	 */
	public static final long name 				= 0x80000000L; 
	/**
	 * фамилия
	 */
	public static final long surname 			= 0x100000000L; 
	/**
	 * отчество
	 */
	public static final long patronymic 		= 0x200000000L;				// , , 
	/**
	 * локативность
	 */
	public static final long locativity 		= 0x400000000L; 
	/**
	 * организация
	 */
	public static final long organization 		= 0x800000000L;				// , 
	/**
	 * качественное прилагательное
	 */
	public static final long quality 			= 0x1000000000L;			// 
	/**
	 * вопросительность (для наречий)
	 */
	public static final long interrogative 		= 0x2000000000L; 
	/**
	 * относительность (для наречий)
	 */
	public static final long relativity 		= 0x4000000000L;			//  и 
	/**
	 * слово обычно не имеет множественного числа
	 */
	public static final long dfst 				= 0x8000000000L; 			// 
	/**
	 * частая опечатка или ошибка
	 */
	public static final long typo 				= 0x10000000000L; 			// 
	/**
	 * жаргонизм
	 */
	public static final long slang 				= 0x20000000000L; 
	/**
	 * архаизм
	 */
	public static final long archaism 			= 0x40000000000L; 
	/**
	 * профессионализм
	 */
	public static final long professionalism 	= 0x80000000000L; 			// , , 
	/**
	 * аббревиатура
	 */
	public static final long acronym 			= 0x100000000000L;			// 
	/**
	 * безличный глагол
	 */
	public static final long impersonal 		= 0x200000000000L;			// 
	/**
	 * превосходная степень 
	 */
	public static final long superlative 		= 0x400000000000L;			// 
	/**
	 * разговорный
	 */
	public static final long informal 			= 0x800000000000L;			// 
	/**
	 * указательное (наречие)
	 */
	public static final long indicating 		= 0x1000000000000L;			// 
	/**
	 * притяжательное (местоимение, прилагательное)
	 */
	public static final long possessive 		= 0x2000000000000L;			// 
}
