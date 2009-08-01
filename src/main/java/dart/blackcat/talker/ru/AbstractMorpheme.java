package dart.blackcat.talker.ru;

import java.util.SortedSet;


public abstract class AbstractMorpheme {

//	public abstract String[] getMorfemes();
	public abstract SortedSet<String> getSortedMorfemes(boolean asc);
}
