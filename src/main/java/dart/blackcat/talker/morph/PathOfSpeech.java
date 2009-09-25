package dart.blackcat.talker.morph;


public enum PathOfSpeech {

	/**
	 * существительное
	 */
	noun(0x1, "существительное"),

	/**
	 * прилагательное
	 */
	adjective(0x2, "прилагательное"), 

	/**
	 * краткое прилагательное
	 */
	shortAdjective(0x4, "краткое прилагательное"),

	/**
	 * глагол
	 */
	verb(0x8, "глагол"), 

	/**
	 * инфинитив (глагола),
	 */
	infinitive(0x10, "инфинитив (глагола)"),

	/**
	 * причастие
	 */
	participle(0x20, "причастие"), 

	/**
	 * краткое причастие
	 */
	shortParticiple(0x40, "краткое причастие"),

	/**
	 * деепричастие
	 */
	gerund(0x80, "деепричастие"),

	/**
	 * местоимение
	 */
	pronoun(0x100, "местоимение"), 

	/**
	 * местоимение-предикатив
	 */
	pronounPredicative(0x200, "местоимение-предикатив"), 

	/**
	 * местоимение прилагательное
	 */
	pronounAdjective(0x400, "местоимение прилагательное"),

	/**
	 * числительное (количественное),
	 */
	numeral(0x800, "числительное (количественное)"), 

	/**
	 * порядковое числительное
	 */
	ordinal(0x1000, "порядковое числительное"),

	/**
	 * наречие
	 */
	adverb(0x2000, "наречие"),

	/**
	 * предикатив
	 */
	predicateNoun(0x4000, "предикатив"),

	/**
	 * предлог
	 */
	preposition(0x8000, "предлог"),

	/**
	 * союз
	 */
	conjunction(0x10000, "союз"),

	/**
	 * междометие
	 */
	interjection(0x20000, "междометие"),

	/**
	 * частица
	 */
	particle(0x40000, "частица"),

	/**
	 * вводное слово
	 */
	introduction(0x80000, "вводное слово"),

	/**
	 * фразеологизм, фразеология
	 */
	phraseology(0x100000, "фразеологизм"),
	
	;
	
	private int code;
	private String name;
	
	private PathOfSpeech(int code, String name) {
		this.code = code;
		this.name = name;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static String resolveCumulativePathOfSpeechName(int code) {
		StringBuffer result = new StringBuffer();
		
		for (PathOfSpeech pathOfSpeech : PathOfSpeech.values()) {
			if ( (pathOfSpeech.code & code) == pathOfSpeech.code) {
				result.append(pathOfSpeech.name).append(" ");
			}
		}
		
		return result.toString();
	}
	
	public static int getCumulativePathOfSpeechCode(PathOfSpeech... pathsOfSpeech) {
		int result = 0;
		for (int i = 0; i < pathsOfSpeech.length; i++) {
			result |= pathsOfSpeech[i].code;
		}
		return result;
	}
	
	public static boolean equals(long pathOfSpeechCode0, long pathOfSpeechCode1, PathOfSpeech... commonPathsOfSpeech) {
		long filter = PathOfSpeech.getCumulativePathOfSpeechCode(commonPathsOfSpeech);
		return (pathOfSpeechCode0 & filter) == (pathOfSpeechCode1 & filter);
	}
	
}
