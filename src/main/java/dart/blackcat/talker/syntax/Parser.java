package dart.blackcat.talker.syntax;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Text parser. 
 * @author pvyazankin
 *
 */
public class Parser {
	
	private static final char[] SENTENCE_DELIMETERS = {'.', '?', '!'};
	private BufferedReader reader;
	
	public Parser(InputStream inputStream) {
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
		char c = 0;
		StringBuffer sentenceString = new StringBuffer();

		NEW_SYMBOL:
		while ((c = (char) reader.read()) != -1) {
			
			for (int i = 0; i < SENTENCE_DELIMETERS.length; i++) {
				if (SENTENCE_DELIMETERS[i] == c) {
					if (sentenceString.length() != 0) {
						sentenceString.append(c);
						return new Sentence(sentenceString.toString().trim());
					} else {
						break NEW_SYMBOL;
					}
				} else {
					sentenceString.append(c);
					break;
				}
			}
		}
		
		return null;
	}
}
