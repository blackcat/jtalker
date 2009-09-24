package dart.blackcat.talker.morph;

public interface PathOfSpeech {
	
	/**
	 * существительное
	 */
	public final static int noun = 0x1;
	
	/**
	 * прилагательное
	 */
	public final static int adjective = 0x2; 
	
	/**
	 * краткое прилагательное
	 */
	public final static int shortAdjective = 0x4;
	
	/**
	 * глагол
	 */
	public final static int verb = 0x8; 
	
	/**
	 * инфинитив (глагола)
	 */
	public final static int infinitive = 0x10;
	
	/**
	 * причастие
	 */
	public final static int participle = 0x20; 
	
	/**
	 * краткое причастие
	 */
	public final static int shortParticiple = 0x40;
	
	
	/**
	 * деепричастие
	 */
	public final static int gerund = 0x60;
	
	/**
	 * местоимение
	 */
	public final static int pronoun = 0x80; 
	
	/**
	 * местоимение-предикатив
	 */
	public final static int pronounPredicative = 0x100; 
	
	/**
	 * местоимение прилагательное
	 */
	public final static int pronounAdjective = 0x200;
	
	/**
	 * числительное (количественное)
	 */
	public final static int numeral = 0x400; 
	
	/**
	 * порядковое числительное
	 */
	public final static int ordinal = 0x800;
	
	/**
	 * наречие
	 */
	public final static int adverb = 0x1000;
	
	/**
	 * предикатив
	 */
	public final static int predicateNoun = 0x2000;
	
	/**
	 * предлог
	 */
	public final static int preposition = 0x4000;
	
	/**
	 * союз
	 */
	public final static int conjunction = 0x8000;
	
	/**
	 * междометие
	 */
	public final static int interjection = 0x10000;
	
	/**
	 * частица
	 */
	public final static int particle = 0x20000;
	
	/**
	 * вводное слово
	 */
	public final static int introduction = 0x40000;
	
	/**
	 * фразеологизм, фразеология
	 */
	public final static int phraseology = 0x80000;
}
