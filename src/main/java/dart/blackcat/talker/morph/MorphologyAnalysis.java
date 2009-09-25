package dart.blackcat.talker.morph;

import static org.springframework.util.Assert.notNull;

import java.io.Serializable;


public class MorphologyAnalysis implements Serializable {

	private static final long serialVersionUID = 5832983868887679325L;
	private String prefix;
	private String base;
	private String flexia;
	private byte accentCharNo;
//	private PathOfSpeech1 pathOfSpeech;
	private PathOfSpeech pathOfSpeech;
//	private Grammema[] grammemas;
	private long grammemas;
	
	public MorphologyAnalysis(
			String prefix,
			String base,
			String flexia,
			byte accentCharNo,
			PathOfSpeech pathOfSpeech,
//			Grammema[] grammemas
			long grammemas
			) {
		notNull(prefix, "Prefix can't be null.");
		notNull(base, "Base can't be null.");
		notNull(flexia, "Flexia can't be null.");
		notNull(pathOfSpeech, "Path of speeck can't be null.");
		notNull(grammemas, "Grammemas array can't be null.");
//		noNullElements(grammemas, "Grammemas array can't contain null elements.");
		
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
//		return prefix + "-" + base + "-" + flexia + " " + accentCharNo + " " + pathOfSpeech + " " + /*Arrays.toString(*/grammemas/*)*/;
		return prefix + "-" + base + "-" + flexia + " " + accentCharNo + " " + pathOfSpeech + " " + Grammema.resolveCumulativeGrammemaName(grammemas);
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

/*	public Grammema[] getGrammemas() {
		return grammemas;
	}*/
	public long getGrammemas() {
		return grammemas;
	}

	public byte getAccent_char_no() {
		return accentCharNo;
	}

	public byte getAccentCharNo() {
		return accentCharNo;
	}

	public void setAccentCharNo(byte accentCharNo) {
		this.accentCharNo = accentCharNo;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public void setFlexia(String flexia) {
		this.flexia = flexia;
	}

	public void setPathOfSpeech(PathOfSpeech pathOfSpeech) {
		this.pathOfSpeech = pathOfSpeech;
	}

/*	public void setGrammemas(Grammema[] grammemas) {
		this.grammemas = grammemas;
	}*/
	public void setGrammemas(long grammemas) {
		this.grammemas = grammemas;
	}
	
	/**
	 * Check if analysis has required grammema. 
	 * @param grammema
	 * @return
	 */
	public boolean hasGrammema(Grammema... grammema) {
		long code = Grammema.getCumulativeGrammemaCode(grammema);
//		return (Grammema.getCumulativeGrammemaCode(grammemas) & code) == code;
		return (grammemas & code) == code;
	}
	
	public boolean hasGrammema(Grammema[] grammemas, Grammema... moreGrammemas) {
		Grammema[] newArr = new Grammema[grammemas.length + moreGrammemas.length];
		System.arraycopy(grammemas, 0, newArr, 0, grammemas.length);
		System.arraycopy(moreGrammemas, 0, newArr, grammemas.length, moreGrammemas.length);
		return hasGrammema(newArr);
	}
	
	public boolean hasGrammema(long code, Grammema... grammemas) {
		long code1 = Grammema.getCumulativeGrammemaCode(grammemas);
//		return (Grammema.getCumulativeGrammemaCode(grammemas) & code) == code;
		return (this.grammemas & code1) == code1;
	}
	
	/**
	 * Check if analyses has required path of speech.
	 * @param pathOfSpeech
	 * @return
	 */
	public boolean hasPathOfSpeech(PathOfSpeech... pathOfSpeech) {
		for (int i = 0; i < pathOfSpeech.length; i++) {
			if (this.pathOfSpeech.equals(pathOfSpeech[i])) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accentCharNo;
		result = prime * result + ((base == null) ? 0 : base.hashCode());
		result = prime * result + ((flexia == null) ? 0 : flexia.hashCode());
		result = prime * result + (int) (grammemas ^ (grammemas >>> 32));
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
		if (grammemas != other.grammemas)
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
