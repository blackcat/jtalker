package dart.blackcat.talker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dart.blackcat.talker.aot.AotHeuristicAnalizer;
import dart.blackcat.talker.morph.MorphologyAnalysis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context.xml"})
public class AotHeuristicAnalizerTest {
	
	@Autowired
	private AotHeuristicAnalizer aotHeuristicAnalizer;
	
	private static final Log log = LogFactory.getLog(AotHeuristicAnalizerTest.class);

	@Test
	public void testAnalyze() throws JTalkerException, IOException {
		
		long startTime = Calendar.getInstance().getTimeInMillis();
		int wordCount = 0;
		Collection<String> bad = new ArrayList<String>();
		Map<String, Set<MorphologyAnalysis>> good = new LinkedHashMap<String, Set<MorphologyAnalysis>>();
		String text = null;
		
		URL url = MorphologyAnalyzerPerformanceTest.class.getResource("/heuristicTest.txt");
		FileReader fileReader = new FileReader(url.getPath());
		try {
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			text = bufferedReader.readLine();
			while (text != null) {
				StringTokenizer st = new StringTokenizer(text, " ,.—:;\"\'\\/!?()[]{}@#№$%^&*_+=0123456789");
				
				while (st.hasMoreTokens()) {
					String word = st.nextToken();
					
					if ( ! word.replace("-", "").isEmpty()) {
						Set<MorphologyAnalysis> set = aotHeuristicAnalizer.analyze(word);
						
						wordCount++;
						log.info(set);
						
						if (set.isEmpty()) {
							bad.add(word);
						} else {
							good.put(word, set);
						}
					}
				}
				
				text = bufferedReader.readLine();
			}
		} finally {
			fileReader.close();
		}
		
		long elapsedTime = ( startTime - Calendar.getInstance().getTimeInMillis() ) / -1;
		log.info("Elapsed time: " + elapsedTime + " ms");
		log.info("Word processed: " + wordCount);
		log.info("Processed succefully: " + good);
		log.info("Processed unsuccefully: " + bad.size());
		log.info("Unsuccefully %: " + bad.size() * 100.000 / wordCount);
		log.info("One word analyze average time: " + elapsedTime * 1.000 / wordCount);
		log.info("Words per second: " + wordCount * 1000.000 / elapsedTime);
		
		Set<String> badSet = new LinkedHashSet<String>(bad);
		log.error(badSet);
	}

}
