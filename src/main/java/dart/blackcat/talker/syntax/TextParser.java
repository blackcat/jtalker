package dart.blackcat.talker.syntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import dart.blackcat.talker.domain.Sentence;


/**
 * Text parser. 
 * @author pvyazankin
 *
 */
public class TextParser {
	
	private static final char[] SENTENCE_DELIMETERS = {'.', '?', '!'};
	private BufferedReader reader;
	
	public TextParser(InputStream inputStream) {
		reader = new BufferedReader(new InputStreamReader(inputStream));
	}
	
	@Override
	protected void finalize() throws Throwable {
		reader.close();
		super.finalize();
	}

	/**
	 * get new sentence 
	 * @return {@link Sentence} or null if there are on sentences found
	 * @throws IOException in case I/O errors
	 */
	public Sentence getSentence() throws IOException {
		int currentChar = 0;
		StringBuffer sentenceString = new StringBuffer();
		StringBuffer eosString = new StringBuffer();
		boolean currentCharIsASentenceDelim = false;
		boolean previousCharIsASentenceDelim = false;

		while ((currentChar = reader.read()) != -1) {
			
			currentCharIsASentenceDelim = false;
			
			for (int i = 0; i < SENTENCE_DELIMETERS.length; i++) {
				if (SENTENCE_DELIMETERS[i] == currentChar) {
					currentCharIsASentenceDelim = true;
					eosString.append((char) currentChar);
				}
			}

			if ( ! currentCharIsASentenceDelim) {
				sentenceString.append((char) currentChar);
				
				if (previousCharIsASentenceDelim) {
					return new Sentence(sentenceString.toString(), eosString.toString());
				}
			}
			
			previousCharIsASentenceDelim = currentCharIsASentenceDelim;
		}
		
		if (currentCharIsASentenceDelim) {
			return new Sentence(sentenceString.toString(), eosString.toString());
		} else {
			return null;
		}
	}
}
