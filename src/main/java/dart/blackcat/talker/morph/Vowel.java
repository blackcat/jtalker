package dart.blackcat.talker.morph;

public enum Vowel {

	а,
	е,
	ё,
	и,
	о,
	у,
	ы,
	э,
	ю,
	я,
	;
	
	public static boolean isVowel(char c) {
		for (Vowel vowel : Vowel.values()) {
			if (vowel.name().equals(c)) {
				return true;
			}
		}
		return false;
	}
}
