package dart.blackcat.talker.ru;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MorphologicalAnalisys implements Serializable {

	private static final Log LOG = LogFactory.getLog(MorphologicalAnalisys.class);
	private static final long serialVersionUID = -7330773290835787138L;
	
	private String 		word;
	private String[] 	prefixes;
	private Set<String> 	roots = new HashSet<String>();
	private String 		interfix;
	private String[] 	suffixes;
	private String		end;
	private WordClass 	wordClass;
	
	private SortedSet<String> 	rootSet = (new Root()).getSortedMorfemes(false);
	private SortedSet<String> 	prefixSet = (new Prefix()).getSortedMorfemes(false);
	private SortedSet<String> 	suffixSet = (Suffix.getInstance()).getSortedMorfemes(false);
	private SortedSet<String> 	interfixSet = (new Interfix()).getSortedMorfemes(false);
	
	private String[] findAllRoots(String word) {
		String prefixesString = null;
		String postfixesString = null;
		
		for (Iterator<String> i = rootSet.iterator(); i.hasNext();) {
			String root = i.next();
			
			if (word.contains(root)) {
				prefixesString = word.substring(0, word.indexOf(root));
				postfixesString = word.substring(word.indexOf(root) + root.length());
				LOG.debug("Root: " + root);
				roots.add(root);
				
				
				for (Iterator<String> j = interfixSet.iterator(); j.hasNext();) {
					String interfix = j.next();
					
					if (prefixesString.endsWith(interfix)) {
						String[] prefixesAndPostfixes = findAllRoots(prefixesString.substring(0, prefixesString.length() - interfix.length()));
						if (prefixesAndPostfixes[0] != null) {
							prefixesString = prefixesAndPostfixes[0];	
						}
					}
					
					if (postfixesString.startsWith(interfix)) {
						String[] prefixesAndPostfixes = findAllRoots(postfixesString.substring(interfix.length()));
						if (prefixesAndPostfixes[1] != null) {
							postfixesString = prefixesAndPostfixes[1];	
						}
					}
				}
			}
		}
		
		return new String[] {prefixesString, postfixesString};
	}
	
	public MorphologicalAnalisys(String word) {
		this.word = word;
		
		String[] prefixesAndPostfixes = findAllRoots(word);
		
		
		
		
//		for (Iterator<String> i = rootSet.iterator(); i.hasNext();) {
//			String root = i.next();
			
//			int position = word.indexOf(root);
//			if (position > -1) {
//				LOG.debug("Root: " + root);	//TODO second root (use interfixSet)
////				roots = new String[] {root};
//				roots.add(root);
				
//				String prefixesString = word.substring(0, position);
				String prefixesString = prefixesAndPostfixes[0];
				LOG.debug("Prefix(es): " + prefixesString);
				if (prefixesString.isEmpty()) {
					prefixes = new String[0];
				} else {
					for (Iterator<String> j = prefixSet.iterator(); j.hasNext();) {
						String prefix = j.next();
						if (prefixesString.equalsIgnoreCase(prefix)) {
							prefixes = new  String[] {prefix};
						}
					}
					if (this.prefixes == null) {
						LOG.error("Complex prefix! not implemented yet!"); // TODO
						prefixes = new  String[] {prefixesString};
					}
				}
				
//				String suffixAndEnd = word.substring(position + root.length());
				String suffixAndEnd = prefixesAndPostfixes[1];
				LOG.debug("SuffixAndEnd: " + suffixAndEnd);
				if (suffixAndEnd.isEmpty()) {
					suffixes = new String[0];
					end = "";
					wordClass = new Verb();
					return;
				} else {
					// TODO suffixes (use suffixSet)	// test
					List<String> wordSuffixList = new ArrayList<String>();
					for (Iterator<String> k = suffixSet.iterator(); k.hasNext();) {
						String suffix = k.next();
						if (suffixAndEnd.startsWith(suffix)) {
							wordSuffixList.add(suffix);
							LOG.debug("Suffix: " + suffix);
							if (wordClass == null) {
								wordClass = Suffix.getInstance().resolveWordClass(suffix);
							}
							suffixAndEnd = suffixAndEnd.substring(suffix.length());
						}
					}
					suffixes = new String[wordSuffixList.size()];
					wordSuffixList.toArray(suffixes);
					
					
					// TODO end		// test
					end = suffixAndEnd;
				}
				
//				wordClass = new Noun(); //TODO plug
//				return;
//			}
//		}
		if (wordClass == null) {
			throw new MorphemeParsingException("Root not found.");	
		}
	}
	
	public String[] getPrefixes() {
		return prefixes;
	}
	public void setPrefixes(String[] prefixes) {
		this.prefixes = prefixes;
	}
	public Set<String> getRoots() {
		return roots;
	}
	public void setRoots(Set<String> roots) {
		this.roots = roots;
	}
	public String getInterfix() {
		return interfix;
	}
	public void setInterfix(String interfix) {
		this.interfix = interfix;
	}
	public String[] getSuffixes() {
		return suffixes;
	}
	public void setSuffixes(String[] suffixes) {
		this.suffixes = suffixes;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public WordClass getWordClass() {
		return wordClass;
	}
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("MorphologicalAnalisys: \n");
		for (int i = 0; i < prefixes.length; i++) {
			sb.append("prefix: ").append(prefixes[i]).append("\n");
		}
		for (Iterator<String> i = roots.iterator(); i.hasNext();) {
			String root = i.next();
			sb.append("root: ").append(root).append("\n");
		}
		for (int i = 0; i < suffixes.length; i++) {
			sb.append("suffix: ").append(suffixes[i]).append("\n");
		}
		if ( ! (end == null || end.isEmpty()) ) {
			sb.append("end: ").append(end).append("\n");	
		}
		return sb.toString();
	}
}
