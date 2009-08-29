package dart.blackcat.talker.aot;

import static org.springframework.util.Assert.*;

import java.io.Serializable;
import java.util.Arrays;

import dart.blackcat.talker.domain.Grammema;
import dart.blackcat.talker.domain.PathOfSpeech;

public class MorphologyAnalysis implements Serializable {

	private static final long serialVersionUID = 5832983868887679325L;
	private String prefix;
	private String base;
	private String flexia;
	private byte accentCharNo;
	private PathOfSpeech pathOfSpeech;
	private Grammema[] grammemas;
	
	public MorphologyAnalysis(
			String prefix,
			String base,
			String flexia,
			byte accentCharNo,
			PathOfSpeech pathOfSpeech,
			Grammema[] grammemas
			) {
		notNull(prefix, "Prefix can't be null.");
		notNull(base, "Base can't be null.");
		notNull(flexia, "Flexia can't be null.");
		notNull(pathOfSpeech, "Path of speeck can't be null.");
		notNull(grammemas, "Grammemas array can't be null.");
		noNullElements(grammemas, "Grammemas array can't contain null elements.");
		
		this.prefix = prefix;
		this.base = base;
		this.flexia = flexia;
		this.accentCharNo = accentCharNo;
		this.pathOfSpeech = pathOfSpeech;
		this.grammemas = grammemas;
	}
	
	public String getWord() {
		return prefix + base + flexia;
	}
	
	@Override
	public String toString() {
		return prefix + "-" + base + "-" + flexia + " " + accentCharNo + " " + pathOfSpeech + " " + Arrays.toString(grammemas);
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
		return accentCharNo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accentCharNo;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((flexia == null) ? 0 : flexia.hashCode());
		result = prime * result + Arrays.hashCode(grammemas);
		result = prime * result
				+ ((pathOfSpeech == null) ? 0 : pathOfSpeech.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MorphologyAnalysis other = (MorphologyAnalysis) obj;
		if (accentCharNo != other.accentCharNo)
			return false;
		if (base == null) {
			if (other.base != null)
				return false;
		} else if (!base.equals(other.base))
			return false;
		if (flexia == null) {
			if (other.flexia != null)
				return false;
		} else if (!flexia.equals(other.flexia))
			return false;
		if (!Arrays.equals(grammemas, other.grammemas))
			return false;
		if (pathOfSpeech == null) {
			if (other.pathOfSpeech != null)
				return false;
		} else if (!pathOfSpeech.equals(other.pathOfSpeech))
			return false;
		if (prefix == null) {
			if (other.prefix != null)
				return false;
		} else if (!prefix.equals(other.prefix))
			return false;
		return true;
	}
	
}
