package dart.blackcat.talker.util;


public abstract class StringUtils extends org.springframework.util.StringUtils {

	public static String nullIfEmpty(String s) {
		return s == null ? s : (s.isEmpty() ? null : s);
	}
	
	public static String emptyIfNull(String s) {
		return s == null ? "" : s;
	}
}
