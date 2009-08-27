package dart.blackcat.talker.aot;

import static org.springframework.util.Assert.*;

import dart.blackcat.talker.domain.Grammema;
import dart.blackcat.talker.domain.PathOfSpeech;

public class MorphologyAnalysis {

	private String prefix;
	private String base;
	private String flexia;
	private byte accent_char_no;
	private PathOfSpeech pathOfSpeech;
	private Grammema[] grammemas;
	
	public MorphologyAnalysis(
			String prefix,
			String base,
			String flexia,
			PathOfSpeech pathOfSpeech,
			Grammema[] grammemas
			) {
		notNull(base);
		notNull(flexia);
		notNull(pathOfSpeech);
		notNull(grammemas);
		noNullElements(grammemas);
		
		this.prefix = prefix;
		this.base = base;
		this.flexia = flexia;
		this.pathOfSpeech = pathOfSpeech;
		this.grammemas = grammemas;
	}

	public String getPrefix() {
		return prefix;
	}

	public String getBase() {
		return base;
	}

	public String getFlexia() {
		return flexia;
	}

	public PathOfSpeech getPathOfSpeech() {
		return pathOfSpeech;
	}

	public Grammema[] getGrammemas() {
		return grammemas;
	}

	public byte getAccent_char_no() {
		return accent_char_no;
	}
	
}
