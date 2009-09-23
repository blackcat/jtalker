package dart.blackcat.talker;

//import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import dart.blackcat.talker.domain.Sentence;
import dart.blackcat.talker.syntax.TextParser;

public class TextParserTest {
	private static final Log log = LogFactory.getLog(TextParserTest.class);

	@Test
	public void testGetSentence() throws IOException {
		URL url = TextParserTest.class.getResource("/0.25test.txt");
		TextParser parser = new TextParser(
				new FileInputStream(
						url.getPath()
				)
		);
		
		int i = 0;
		Sentence sentence;
		while ((sentence = parser.getSentence()) != null) {
			i++;
			log.info(sentence.asString());
			log.info(sentence);
		}
		log.info(i);
	}

}
