package dart.blackcat.talker.syntax;

import java.io.Serializable;
import java.util.Iterator;
import java.util.StringTokenizer;

/**
 * Basic sentence implementation
 * @author pvyazankin
 *
 */
public class Sentence  implements Serializable, Iterable<String> {

	private static final long serialVersionUID = 8073040487942891802L;
	
	
	private String s;
	private char[] eosChars;
	
	private boolean isInterrogative = false;		// вопросительное предложение
	private boolean isExclamatory = false;			// восклицательное предложение
	
	
	
	public Sentence(String sentence, String eosString) {
		s = sentence.trim();
		eosChars = eosString.trim().toCharArray();
		
		for (int i = 0; i < eosChars.length; i++) {
			if (eosChars[i] == '?') {
				isInterrogative = true; 
			}
			if (eosChars[i] == '!') {
				isExclamatory = true;
			}
		}
	}
	
	
	public boolean isInterrogative() {
		return isInterrogative;
	}
	
	
	public boolean isExclamatory() {
		return isExclamatory;
	}

	
	public String asString() {
		StringBuffer sb = new StringBuffer(s);
		for (int i = 0; i < eosChars.length; i++) {
			sb.append(eosChars[i]);
		}
		return sb.toString();
	}


	/**
	 * This iterator doesn't support remove().
	 * @return iterator
	 */
	@Override
	public Iterator<String> iterator() {
		return new WordIterator();
	}
	
	
	private class WordIterator implements Iterator<String> {
		
		private StringTokenizer tokenizer = new StringTokenizer(s, " 	,.—:;\"\'\\/!?()[]{}@#№$%^&*_+=0123456789", false);
		
		@Override
		public boolean hasNext() {
			return tokenizer.hasMoreTokens();
		}

		@Override
		public String next() {
			return tokenizer.nextToken();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Из песни слова не выкинешь!");
		}
		
	}
	
	@Override
	public String toString() {
		StringBuffer sb = s.length() > 15 ? new StringBuffer(s.substring(0, 15)).append("... ") : new StringBuffer(s);
		for (int i = 0; i < eosChars.length; i++) {
			sb.append(eosChars[i]);
		}
		sb.append(" isInterrogative=").append(isInterrogative);
		sb.append(" isExclamatory=").append(isExclamatory);
		return sb.toString();
	}
}
