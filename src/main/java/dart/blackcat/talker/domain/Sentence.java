package dart.blackcat.talker.domain;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Basic sentence implementation.
 * @author pvyazankin
 *
 */
public class Sentence  implements Serializable, Iterable<Word> {

	private static final long serialVersionUID = 8073040487942891802L;
	
	
	private String s;
	private char[] eosChars;
	private List<Word> words;
	
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
		
		StringTokenizer tokenizer = new StringTokenizer(s, " 	,.—:;\"\'\\/!?()[]{}@#№$%^&*_+=0123456789", false);
		while (tokenizer.hasMoreTokens()) {
			words.add(new Word(tokenizer.nextToken()));
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
	public Iterator<Word> iterator() {
		return words.iterator();
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(eosChars);
		result = prime * result + (isExclamatory ? 1231 : 1237);
		result = prime * result + (isInterrogative ? 1231 : 1237);
		result = prime * result + ((s == null) ? 0 : s.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sentence other = (Sentence) obj;
		if (!Arrays.equals(eosChars, other.eosChars))
			return false;
		if (isExclamatory != other.isExclamatory)
			return false;
		if (isInterrogative != other.isInterrogative)
			return false;
		if (s == null) {
			if (other.s != null)
				return false;
		} else if (!s.equals(other.s))
			return false;
		return true;
	}
	
	
}
