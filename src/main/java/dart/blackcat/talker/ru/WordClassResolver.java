package dart.blackcat.talker.ru;

public class WordClassResolver {

	public WordClass resolve(String word) {
		if (Pronoun.isPronoun(word)) {
			return new Pronoun();
		} else {
			String[] roots = (new Root()).getMorfemes();
			
			for (int i = 0; i < roots.length; i++) {
				
			}
//			if ()
		}
		return null;
	}
}
