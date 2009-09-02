package dart.blackcat.talker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dart.blackcat.talker.aot.AotException;
import dart.blackcat.talker.aot.AotMorphologyAnalyzer;
import dart.blackcat.talker.domain.MorphologyAnalysis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context.xml"})
public class MorphologyAnalyzerPerformanceTest {

	@Autowired
	AotMorphologyAnalyzer morphologyAnalyzer;
	
	private static final Log log = LogFactory.getLog(MorphologyAnalyzerPerformanceTest.class);
	
	@Test
	public void testAnalyze() throws IOException, AotException {
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		int wordCount = 0;
		int good = 0;
		Collection<String> bad = new LinkedHashSet<String>();
		String text = null;
		
		URL url = MorphologyAnalyzerPerformanceTest.class.getResource("/test.txt");
		FileReader fileReader = new FileReader(url.getPath());
		try {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			text = bufferedReader.readLine();
			while (text != null) {
				StringTokenizer st = new StringTokenizer(text, " ,.-—:;\"\'\\/!?()[]{}@#№$%^&*_+=");
				
				while (st.hasMoreTokens()) {
					String word = st.nextToken();
					Set<MorphologyAnalysis> set = morphologyAnalyzer.analyze(word);
					wordCount++;
					log.info(set);
					
					if (set.isEmpty()) {
						bad.add(word);
					} else {
						good++;
					}
				}
				
				text = bufferedReader.readLine();
			}
		} catch (IOException e) {
			throw e;
		} catch (AotException e) {
			throw e;
		} finally {
			fileReader.close();
		}
		
		long elapsedTime = startTime - Calendar.getInstance().getTimeInMillis();
		log.info("Word processed: " + wordCount);
		log.info("Processed succefully: " + good);
		log.info("Processed unsuccefully: " + bad.size());
		log.info("Elapsed time: " + elapsedTime);
		
		log.error(bad);
	}

}
