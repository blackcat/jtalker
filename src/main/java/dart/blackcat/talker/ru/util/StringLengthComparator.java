package dart.blackcat.talker.ru.util;

import java.util.Comparator;

public class StringLengthComparator implements Comparator<String> {
	
	private boolean asc = true;
	
	public StringLengthComparator(boolean asc) {
		this.asc = asc;
	}

	@Override
	public int compare(String o1, String o2) {
		int l1 = o1.length();
		int l2 = o2.length();
		if (l1 == l2) {
			return o1.compareToIgnoreCase(o2);
		} else {
			if (asc) {
				return l1 > l2 ? 1 : -1;	
			} else {
				return l1 < l2 ? 1 : -1;
			}
		}
	}

}
