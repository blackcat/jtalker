package dart.blackcat.talker;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dart.blackcat.talker.ru.WordClass;
import dart.blackcat.talker.ru.WordClassResolver;


/**
 * Hello world!
 *
 */
public class Boot {

	private static final Log LOG = LogFactory.getLog(Boot.class);
	
	protected final static String WORD_CLASS_RESOLVER_BEANNAME = "wordClassResolver";
	
    public static void main( String[] args ) {
    	String inputText = args[0];
    	
		LOG.info("Starting Talker application.");
		LOG.info("Creating Srping application context.");
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"context.xml"});
		BeanFactory factory = context;
		
		// arguments parse
		List<String> argsList = new ArrayList<String>();
		for (int i = 0; i < args.length; i++) {
			argsList.add(i, args[i]);
		}
		
		// start!
		WordClassResolver wordClassResolver;
		if (factory.containsBean(WORD_CLASS_RESOLVER_BEANNAME)) {
			wordClassResolver = (WordClassResolver) factory.getBean(WORD_CLASS_RESOLVER_BEANNAME);
		} else {
			LOG.error("No word class resolver beans found!");
			return;
		}
		
		LOG.info(inputText);
		String[] words = inputText.split("\\s");
		for (int i = 0; i < words.length; i++) {
			WordClass wordClass = wordClassResolver.resolve(words[i]);
			LOG.info(words[i] + " : " + wordClass.getClass().getSimpleName());
		}
    }
}
