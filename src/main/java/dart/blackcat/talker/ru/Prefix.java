package dart.blackcat.talker.ru;

import java.util.SortedSet;
import java.util.TreeSet;

import dart.blackcat.talker.ru.util.StringLengthComparator;

public class Prefix extends AbstractAffix {
	
	private final static String[] S = new String[] {
		// приставки для кратных единиц
		"дека",	"гекто", "кило", "мега", "гига", "тера", "пета", "экса", "зетта","йотта",
		// приставки для дольных единиц
		"деци",	"санти", "милли", "микро", "нано", "пико", "фемто", "атто", "зепто", "йокто",
		// приставки первой группы
		"в", "во", "взо", "вы", "до", "за", "изо", "ко", "на", "над", "надо", "не", "недо", "о", "об", "обо", "от", "ото", "па", "по", "под", "подо", "пра", "пред", "предо", "про", "разо", "с", "со", "су", "у",
		// приставки второй группы
		"без", "бес", "вз", "вс", "воз", "вос", "из", "ис", "низ", "нис", "раз", "рас", "роз", "рос", "через", "черес",
		// приставки третьей группы
		"пре", "при",
	};

//	@Override
//	public String[] getMorfemes() {
//		return S; 
//	}

	@Override
	public SortedSet<String> getSortedMorfemes(boolean asc) {
		SortedSet<String> set = new TreeSet<String>(new StringLengthComparator(asc));
		for (int i = 0; i < S.length; i++) {
			set.add(S[i]);
		}
		return set;
	}

}
