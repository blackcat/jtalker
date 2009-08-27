package dart.blackcat.talker;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * Hello world!
 *
 */
public class Boot {

	private static final Log LOG = LogFactory.getLog(Boot.class);
	
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
		// TODO
		
		LOG.info(inputText);
		String[] words = inputText.split("\\s");
		for (int i = 0; i < words.length; i++) {
			// TODO
		}
    }
}
