package dart.blackcat.talker.syntax;

/**
 * Path of sentence implementation.
 * @author pvyazankin
 *
 */
public enum PartOfSentence {

	/**
	 * подлежащее
	 */
	subject(0x1, "подлежащее"),
	
	/**
	 * сказуемое
	 */
	predicate(0x2, "сказуемое"),
	
	/**
	 * дополнение
	 */
	object(0x4, "дополнение"),
	
	/**
	 * обстоятельство
	 */
	adverbialModifier(0x8, "обстоятельство"),
	
	/**
	 * определение
	 */
	attribute(0x10, "определение"),
	
	;
	
	private int code;
	private String name;
	
	private PartOfSentence(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

}
