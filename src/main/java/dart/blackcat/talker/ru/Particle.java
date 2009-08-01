package dart.blackcat.talker.ru;

public class Particle extends WordClass {
	
	private static final String[] S = {
		"а", "благо", "более", "больше", "буквально", "бывает", "бывало", "было", "будто", "ведь", "во",
		"вовсе", "вон", "вот", "вроде", "всё", "всего", "где", "гляди", "да", "давай", "давайте", "даже", "дай", 
		"дайте", "действительно", "единственно", "если", "еще", "знай", "и", "или", "именно", "как", "какое", 
		"куда", "ладно", "ли", "лучше", "никак", "ничего", "нечего", "но", "однако", "окончательно", "оно", 
		"поди", "положительно", "просто", "прямо", "пусть", "пускай", "разве", "решительно", "ровно", "самое", 
		"себе", "скорее", "словно", "совершенно", "спасибо", "так", "там", "тебе", "тоже", "только", "точно", 
		"хоть", "чего", "чисто", "что", "чтоб", "чтобы", "эк", "это",
	};

	public static boolean isParticle(String word) {
		for (int i = 0; i < S.length; i++) {
			if (S[i].compareToIgnoreCase(word) == 0) {
				return true;
			}
		}
		return false;
	}
}
