package dart.blackcat.talker.ru;


public class Pronoun extends WordClass {
	
	private static final String[] S = {
		"я", "мы", "ты", "Вы", "вы", "он", "она", "оно", "они",
		"меня", "нас", "тебя", "Вас", "вас", "его", "ее", "его", "их",
		"мне", "нам", "тебе", "Вам", "вам", "ему", "ей", "ему", "им",
		"меня", "нас", "тебя", "Вас", "вас", "его", "ее", "его", "их",
		"мной", "нами", "тобой", "Вами", "вами", "им", "ей", "им", "ими",
		// п
	};

	public static boolean isPronoun(String word) {
		for (int i = 0; i < S.length; i++) {
			if (S[i].compareToIgnoreCase(word) == 0) {
				return true;
			}
		}
		return false;
	}
}
