package dart.blackcat.talker;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dart.blackcat.talker.domain.Sentence;
import dart.blackcat.talker.syntax.SyntacticAnalysis;
import dart.blackcat.talker.syntax.SyntacticAnalyzer;
import dart.blackcat.talker.syntax.TextParser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context.xml"})
public class SyntacticAnalyzerTest {
	
	@Autowired
	@Qualifier("syntacticAnalyzerQueue")
	SyntacticAnalyzer syntacticAnalyzer;
	
	private static final Log log = LogFactory.getLog(SyntacticAnalyzerTest.class);
	
	@Test
	public void testAnalyze() throws JTalkerException, IOException {
		
		URL url = TextParserTest.class.getResource("/easy-test.txt");
		TextParser parser = new TextParser(
				new FileInputStream(
						url.getPath()
				)
		);
		
		Sentence sentence;
		while ((sentence = parser.getSentence()) != null) {
			Set<SyntacticAnalysis> set = syntacticAnalyzer.analyze(sentence);
			log.info(set);
		}
		
	}
}
