package dart.blackcat.talker;

import java.util.LinkedHashMap;
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

import dart.blackcat.talker.aot.AotException;
import dart.blackcat.talker.aot.AotMorphologyAnalyzer;
import dart.blackcat.talker.domain.MorphologyAnalysis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context.xml"})
public class MorphologyAnalyzerTest {

	@Autowired
	AotMorphologyAnalyzer morphologyAnalyzer;
	
	private static final Log log = LogFactory.getLog(MorphologyAnalyzerTest.class);
	
	@Test
	public void testAnalyze() throws AotException {
		
		String text = "Морфологический анализатор для русского языка — это что-то заумное? Программа, которая приводит слово к начальной форме, определяет падеж, находит словоформы — непонятно, как и подступиться? А на самом деле все не так и сложно. В статье — как я писал аналог mystem, lemmatizer и phpmorphy на Python, и что из этого получилось.";
		StringTokenizer st = new StringTokenizer(text, " ,.-—:;\"\'\\/!?()[]{}@#№$%^&*_+=");
		Map<String, Set<MorphologyAnalysis>> result = new LinkedHashMap<String, Set<MorphologyAnalysis>>();
		
		while (st.hasMoreTokens()) {
			String word = st.nextToken();
			Set<MorphologyAnalysis> set = morphologyAnalyzer.analyze(word);
			log.info(set);
			result.put(word, set);
		}
		log.info(result);
	}

}
