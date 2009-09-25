package dart.blackcat.talker;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
public class SyntacticAnalyzerPerformanceTest {
	
	@Autowired
	@Qualifier("syntacticAnalyzerQueue")
	SyntacticAnalyzer syntacticAnalyzer;
	
	private static final Log log = LogFactory.getLog(SyntacticAnalyzerPerformanceTest.class);
	
	@Test
	public void testAnalyze() throws JTalkerException, IOException {
		
		URL url = TextParserTest.class.getResource("/0.25test.txt");
		TextParser parser = new TextParser(
				new FileInputStream(
						url.getPath()
				)
		);
		
		int sentenceCount = 0;
		int good = 0;
		List<Sentence> badSentences = new ArrayList<Sentence>();
		long startTime = System.currentTimeMillis();
		
		Sentence sentence;
		while ((sentence = parser.getSentence()) != null) {
			sentenceCount++;
			
			Set<SyntacticAnalysis> set = syntacticAnalyzer.analyze(sentence);
			log.info(set);
			
			if (set.isEmpty()) {
				badSentences.add(sentence);
			} else {
				good++;
			}
		}
		
		long time = System.currentTimeMillis() - startTime;
		
		log.info("------");
		log.info("time elapsled: " + time);
		log.info("sentences processed: " + sentenceCount);
		log.info("succ / unsucc %: " + good * 100.000 / badSentences.size());
		log.info("ms / sentence: " + time / sentenceCount);
		log.info("sentence / s: " + sentenceCount * 1000.000 / time);
	}
}
