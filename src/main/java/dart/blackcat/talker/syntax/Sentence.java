package dart.blackcat.talker.syntax;

import java.io.Serializable;

/**
 * Basic sentence implementation
 * @author pvyazankin
 *
 */
public class Sentence  implements Serializable {

	private static final long serialVersionUID = 8073040487942891802L;
	
	
	private String s;
	
	public Sentence(String sentence) {
		s = sentence;
	}
	
	public String asString() {
		return s;
	}
}
