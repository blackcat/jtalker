package dart.blackcat.talker.ru;

import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.util.Assert;

import dart.blackcat.talker.ru.util.StringLengthComparator;

public class Suffix extends AbstractAffix {
	
/*	private static final String[] S = new String[] {
		// суффиксы существительных
		"ан", "ян", "анин", "янин", "ач", "ени", "ет", "еств", "ств", "есть", "ец", "изм", "изн", "ик", "ник", "ин", "ист", "итель", "тель", "их", "иц", "ниц", "к", "л", "лк", "льник", "льщик", "льщиц", "ни", "от", "ость", "ун", "чик", "чиц", "щик",
		// суффиксы прилагательных
		"ал", "ел", "ан", "ян", "аст", "ат", "ев", "ов", "еват", "оват", "ен", "енн", "онн", "енск", "инск", "ив", "лив", "чив", "ин", "ист", "ит", "овит", "к", "л", "н", "шн", "ельн", "уч", "юч", "яч", "чат",
		// суффиксы глаголов
		"а", "я", "ка", "е", "ева", "ова", "ыва", "ива", "и", "л", "нича", "ну", "ствова", "ся", "ть", "ти",
		// суффиксы наречий
		"а", "е", "ейш", "айш", "и", "жды", "либо", "нибудь", "о", "то", "учи", "ючи", "ик",
		// суффиксы местоимений 
		"либо", "нибудь", "то", "б", "а", "к", "а", "ни", "е", "ени", "е", "ск", "ий", "ов", "ск", "ий", "ан", "ск", "ий", "иан", "ск", "ий", "ин", "ск", "ий", "ий", "ск", "ий", "тельн", "ый", "тель", "ств", "о",
	};*/
	private static Suffix INSTANCE = null;
	
	private static final SortedMap<String, WordClass> SUFFIX_MAP = new TreeMap<String, WordClass>(new StringLengthComparator(false));
	private static final Noun		NOUN		= new Noun();
	private static final Pronoun	PRONOUN		= new Pronoun();
	private static final Verb		VERB		= new Verb();
	private static final Adverb		ADVERB		= new Adverb();
	private static final Adjective	ADJECTIVE	= new Adjective();
	
	public static final synchronized Suffix getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Suffix();
		}
		return INSTANCE;
	}
	
	private Suffix() {
		
		// nouns
		SUFFIX_MAP.put("ан", NOUN);
		SUFFIX_MAP.put("ян", NOUN);
		SUFFIX_MAP.put("анин", NOUN);
		SUFFIX_MAP.put("янин", NOUN);
		SUFFIX_MAP.put("ач", NOUN);
		SUFFIX_MAP.put("ени", NOUN);
		SUFFIX_MAP.put("ет", NOUN);
		SUFFIX_MAP.put("еств", NOUN);
		SUFFIX_MAP.put("ств", NOUN);
		SUFFIX_MAP.put("есть", NOUN);
		SUFFIX_MAP.put("ец", NOUN);
		SUFFIX_MAP.put("изм", NOUN);
		SUFFIX_MAP.put("изн", NOUN);
		SUFFIX_MAP.put("ик", NOUN);
		SUFFIX_MAP.put("ник", NOUN);
		SUFFIX_MAP.put("ин", NOUN);
		SUFFIX_MAP.put("ист", NOUN);
		SUFFIX_MAP.put("итель", NOUN);
		SUFFIX_MAP.put("тель", NOUN);
		SUFFIX_MAP.put("их", NOUN);
		SUFFIX_MAP.put("иц", NOUN);
		SUFFIX_MAP.put("ниц", NOUN);
		SUFFIX_MAP.put("к", NOUN);
		SUFFIX_MAP.put("л", NOUN);
		SUFFIX_MAP.put("лк", NOUN);
		SUFFIX_MAP.put("льник", NOUN);
		SUFFIX_MAP.put("льщик", NOUN);
		SUFFIX_MAP.put("льщиц", NOUN);
		SUFFIX_MAP.put("ни", NOUN);
		SUFFIX_MAP.put("от", NOUN);
		SUFFIX_MAP.put("ость", NOUN);
		SUFFIX_MAP.put("ун", NOUN);
		SUFFIX_MAP.put("чик", NOUN);
		SUFFIX_MAP.put("чиц", NOUN);
		SUFFIX_MAP.put("щик", NOUN);
		
		// adjectives
		SUFFIX_MAP.put("ал", ADJECTIVE);
		SUFFIX_MAP.put("ел", ADJECTIVE);
		SUFFIX_MAP.put("ан", ADJECTIVE);
		SUFFIX_MAP.put("ян", ADJECTIVE);
		SUFFIX_MAP.put("аст", ADJECTIVE);
		SUFFIX_MAP.put("ат", ADJECTIVE);
		SUFFIX_MAP.put("ев", ADJECTIVE);
		SUFFIX_MAP.put("ов", ADJECTIVE);
		SUFFIX_MAP.put("еват", ADJECTIVE);
		SUFFIX_MAP.put("оват", ADJECTIVE);
		SUFFIX_MAP.put("ен", ADJECTIVE);
		SUFFIX_MAP.put("енн", ADJECTIVE);
		SUFFIX_MAP.put("онн", ADJECTIVE);
		SUFFIX_MAP.put("енск", ADJECTIVE);
		SUFFIX_MAP.put("инск", ADJECTIVE);
		SUFFIX_MAP.put("ив", ADJECTIVE);
		SUFFIX_MAP.put("лив", ADJECTIVE);
		SUFFIX_MAP.put("чив", ADJECTIVE);
		SUFFIX_MAP.put("ин", ADJECTIVE);
		SUFFIX_MAP.put("ист", ADJECTIVE);
		SUFFIX_MAP.put("ит", ADJECTIVE);
		SUFFIX_MAP.put("овит", ADJECTIVE);
		SUFFIX_MAP.put("к", ADJECTIVE);
		SUFFIX_MAP.put("л", ADJECTIVE);
		SUFFIX_MAP.put("н", ADJECTIVE);
		SUFFIX_MAP.put("шн", ADJECTIVE);
		SUFFIX_MAP.put("ельн", ADJECTIVE);
		SUFFIX_MAP.put("уч", ADJECTIVE);
		SUFFIX_MAP.put("юч", ADJECTIVE);
		SUFFIX_MAP.put("яч", ADJECTIVE);
		SUFFIX_MAP.put("чат", ADJECTIVE);
		
		// verbs
		SUFFIX_MAP.put("а", VERB);
		SUFFIX_MAP.put("я", VERB);
		SUFFIX_MAP.put("ка", VERB);
		SUFFIX_MAP.put("е", VERB);
		SUFFIX_MAP.put("ева", VERB);
		SUFFIX_MAP.put("ова", VERB);
		SUFFIX_MAP.put("ыва", VERB);
		SUFFIX_MAP.put("ива", VERB);
		SUFFIX_MAP.put("и", VERB);
		SUFFIX_MAP.put("л", VERB);
		SUFFIX_MAP.put("нича", VERB);
		SUFFIX_MAP.put("ну", VERB);
		SUFFIX_MAP.put("ствова", VERB);
		SUFFIX_MAP.put("ся", VERB);
		SUFFIX_MAP.put("ть", VERB);
		SUFFIX_MAP.put("ти", VERB);

		// adverbs
//		suffixMap.put("а", adverb);
//		suffixMap.put("е", adverb);
		SUFFIX_MAP.put("ейш", ADVERB);
		SUFFIX_MAP.put("айш", ADVERB);
		SUFFIX_MAP.put("и", ADVERB);
		SUFFIX_MAP.put("жды", ADVERB);
		SUFFIX_MAP.put("либо", ADVERB);
		SUFFIX_MAP.put("нибудь", ADVERB);
		SUFFIX_MAP.put("о", ADVERB);
		SUFFIX_MAP.put("то", ADVERB);
		SUFFIX_MAP.put("учи", ADVERB);
		SUFFIX_MAP.put("ючи", ADVERB);
		SUFFIX_MAP.put("ик", ADVERB);
		
		// pronoun
		SUFFIX_MAP.put("либо", PRONOUN);
		SUFFIX_MAP.put("нибудь", PRONOUN);
		SUFFIX_MAP.put("то", PRONOUN);
		SUFFIX_MAP.put("б", PRONOUN);
//		suffixMap.put("а", pronoun);
		SUFFIX_MAP.put("к", PRONOUN);
		SUFFIX_MAP.put("ни", PRONOUN);
		SUFFIX_MAP.put("ени", PRONOUN);
//		suffixMap.put("е", pronoun);
		SUFFIX_MAP.put("ск", PRONOUN);
		SUFFIX_MAP.put("ий", PRONOUN);
		SUFFIX_MAP.put("ов", PRONOUN);
		SUFFIX_MAP.put("ск", PRONOUN);
		SUFFIX_MAP.put("ий", PRONOUN);
		SUFFIX_MAP.put("ан", PRONOUN);
		SUFFIX_MAP.put("ск", PRONOUN);
		SUFFIX_MAP.put("ий", PRONOUN);
		SUFFIX_MAP.put("иан", PRONOUN);
		SUFFIX_MAP.put("ск", PRONOUN);
		SUFFIX_MAP.put("ий", PRONOUN);
		SUFFIX_MAP.put("ин", PRONOUN);
		SUFFIX_MAP.put("ск", PRONOUN);
		SUFFIX_MAP.put("ий", PRONOUN);
		SUFFIX_MAP.put("ий", PRONOUN);
		SUFFIX_MAP.put("ск", PRONOUN);
		SUFFIX_MAP.put("ий", PRONOUN);
		SUFFIX_MAP.put("тельн", PRONOUN);
		SUFFIX_MAP.put("ый", PRONOUN);
		SUFFIX_MAP.put("тель", PRONOUN);
		SUFFIX_MAP.put("ств", PRONOUN);
//		suffixMap.put("о", pronoun);
	}

//	@Override
//	public String[] getMorfemes() {
//		return S; 
//	}

	@Override
	public SortedSet<String> getSortedMorfemes(boolean asc) {
		SortedSet<String> set = new TreeSet<String>(new StringLengthComparator(asc));
		set.addAll(SUFFIX_MAP.keySet());
		return set;
	}
	
	/**
	 * Resolve {@link WordClass} by suffix.
	 * @param suffix one suffix
	 * @return {@link WordClass} or null 
	 * @throws NullPointerException if suffix is null.
	 */
	public WordClass resolveWordClass(String suffix) throws NullPointerException {
		Assert.notNull(suffix, "Suffix can't be null.");
		return SUFFIX_MAP.get(suffix);
	}

}
