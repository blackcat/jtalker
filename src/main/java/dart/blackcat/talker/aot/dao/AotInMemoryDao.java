package dart.blackcat.talker.aot.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dart.blackcat.talker.MorphologyAnalyzerPerformanceTest;
import dart.blackcat.talker.aot.AotException;
import dart.blackcat.talker.morph.MorphologyAnalysis;

public class AotInMemoryDao extends AbstractAotDao {
	
	private Map<String, LemmataObj> lemmata = new HashMap<String, LemmataObj>();
	private Map<Integer, FlexiaObj> flexia = new HashMap<Integer, FlexiaObj>();
	private Map<String, Object> ancode = new HashMap<String, Object>();
	private Map<Integer, Object> accent = new HashMap<Integer, Object>();
	
	private static final Log log = LogFactory.getLog(MorphologyAnalyzerPerformanceTest.class);
	
	public AotInMemoryDao(String lemmataFilePath, String flexiaFilePath, String ancodeFilePath, String accentFilePath) throws AotException {
		
		URL url;
		String text = null;
		FileReader fileReader;
		try {
			// lemmata
			url = AotInMemoryDao.class.getResource(lemmataFilePath);
			fileReader = new FileReader(url.getPath());
			try {
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				text = bufferedReader.readLine();
				Pattern pattern = Pattern.compile("^(.*?)\\s(.*?)\\s(.*?)\\s(.*?)\\s(.*?)\\s");
				
				while (text != null) {
					Matcher matcher = pattern.matcher(text);
					matcher.find();
					LemmataObj lemmataObj = new LemmataObj();
					lemmataObj.flexiaId = Integer.valueOf(matcher.group(2));
					lemmataObj.accentId = Integer.valueOf(matcher.group(3));
					lemmataObj.prefixId = Integer.valueOf(matcher.group(4));
					lemmataObj.ancodeId = matcher.group(5);
					lemmata.put(matcher.group(1), lemmataObj);
					text = bufferedReader.readLine();
				}
			} finally {
				fileReader.close();
				log.info(lemmata.size() + " lemmas loaded.");
			}
			
			
			// flexia
			url = AotInMemoryDao.class.getResource(flexiaFilePath);
			fileReader = new FileReader(url.getPath());
			
			try {
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				text = bufferedReader.readLine();
				Pattern pattern = Pattern.compile("^(.*?)\\s(.*?)\\s(.*?)\\s(.*?)");
				
				while (text != null) {
					Matcher matcher = pattern.matcher(text);
					matcher.find();
					FlexiaObj flexiaObj = new FlexiaObj();
					flexiaObj.formNo = Integer.valueOf(matcher.group(2));
					flexiaObj.flexia = matcher.group(3);
					flexiaObj.ancodeId = matcher.group(4);
					flexia.put(Integer.valueOf(matcher.group(1)), flexiaObj);
					text = bufferedReader.readLine();
				}
			} finally {
				fileReader.close();
				log.info(flexia.size() + " flexias loaded.");
			}
			
			
			// ancode
			url = AotInMemoryDao.class.getResource(flexiaFilePath);
			fileReader = new FileReader(url.getPath());
			
			try {
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				text = bufferedReader.readLine();
				Pattern pattern = Pattern.compile("^(.*?)\\s(.*?)\\s(.*?)");
				
				while (text != null) {
					Matcher matcher = pattern.matcher(text);
					matcher.find();
					AncodeObj ancodeObj = new AncodeObj();
					ancodeObj.pathOfSpeech = matcher.group(2);
					ancodeObj.morphems = matcher.group(3);
					ancode.put(matcher.group(1), ancodeObj);
					text = bufferedReader.readLine();
				}
			} finally {
				fileReader.close();
				log.info(ancode.size() + " ancodes loaded.");
			}
		} catch (FileNotFoundException e) {
			throw new AotException(e);
		} catch (IOException e) {
			throw new AotException(e);
		}
	}
	
	@Override
	public Set<MorphologyAnalysis> findWord(String lemma, String flexia) throws AotException {
		lemmata.get(lemma);//TODO
		return null;
	}

	
	
	/*-- UTILS --*/
	
	protected class AncodeObj {
		private String pathOfSpeech;
		private String morphems;
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((morphems == null) ? 0 : morphems.hashCode());
			result = prime * result
					+ ((pathOfSpeech == null) ? 0 : pathOfSpeech.hashCode());
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
			AncodeObj other = (AncodeObj) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (morphems == null) {
				if (other.morphems != null)
					return false;
			} else if (!morphems.equals(other.morphems))
				return false;
			if (pathOfSpeech == null) {
				if (other.pathOfSpeech != null)
					return false;
			} else if (!pathOfSpeech.equals(other.pathOfSpeech))
				return false;
			return true;
		}
		public String getPathOfSpeech() {
			return pathOfSpeech;
		}
		public void setPathOfSpeech(String pathOfSpeech) {
			this.pathOfSpeech = pathOfSpeech;
		}
		public String getMorphems() {
			return morphems;
		}
		public void setMorphems(String morphems) {
			this.morphems = morphems;
		}
		private AotInMemoryDao getOuterType() {
			return AotInMemoryDao.this;
		}
	}
	
	protected class FlexiaObj {
		private Integer formNo;
		private String prefix;
		private String flexia;
		private String ancodeId;
		public Integer getFormNo() {
			return formNo;
		}
		public void setFormNo(Integer formNo) {
			this.formNo = formNo;
		}
		public String getPrefix() {
			return prefix;
		}
		public void setPrefix(String prefix) {
			this.prefix = prefix;
		}
		public String getFlexia() {
			return flexia;
		}
		public void setFlexia(String flexia) {
			this.flexia = flexia;
		}
		public String getAncodeId() {
			return ancodeId;
		}
		public void setAncodeId(String ancodeId) {
			this.ancodeId = ancodeId;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((ancodeId == null) ? 0 : ancodeId.hashCode());
			result = prime * result
					+ ((flexia == null) ? 0 : flexia.hashCode());
			result = prime * result
					+ ((formNo == null) ? 0 : formNo.hashCode());
			result = prime * result
					+ ((prefix == null) ? 0 : prefix.hashCode());
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
			FlexiaObj other = (FlexiaObj) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (ancodeId == null) {
				if (other.ancodeId != null)
					return false;
			} else if (!ancodeId.equals(other.ancodeId))
				return false;
			if (flexia == null) {
				if (other.flexia != null)
					return false;
			} else if (!flexia.equals(other.flexia))
				return false;
			if (formNo == null) {
				if (other.formNo != null)
					return false;
			} else if (!formNo.equals(other.formNo))
				return false;
			if (prefix == null) {
				if (other.prefix != null)
					return false;
			} else if (!prefix.equals(other.prefix))
				return false;
			return true;
		}
		private AotInMemoryDao getOuterType() {
			return AotInMemoryDao.this;
		}
	}
	
	protected class LemmataObj {
		private Integer flexiaId;
		private Integer accentId;
		private Integer prefixId;
		private String	ancodeId;
		public Integer getFlexiaId() {
			return flexiaId;
		}
		public void setFlexiaId(Integer flexiaId) {
			this.flexiaId = flexiaId;
		}
		public Integer getAccentId() {
			return accentId;
		}
		public void setAccentId(Integer accentId) {
			this.accentId = accentId;
		}
		public Integer getPrefixId() {
			return prefixId;
		}
		public void setPrefixId(Integer prefixId) {
			this.prefixId = prefixId;
		}
		public String getAncodeId() {
			return ancodeId;
		}
		public void setAncodeId(String ancodeId) {
			this.ancodeId = ancodeId;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result
					+ ((accentId == null) ? 0 : accentId.hashCode());
			result = prime * result
					+ ((ancodeId == null) ? 0 : ancodeId.hashCode());
			result = prime * result
					+ ((flexiaId == null) ? 0 : flexiaId.hashCode());
			result = prime * result
					+ ((prefixId == null) ? 0 : prefixId.hashCode());
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
			LemmataObj other = (LemmataObj) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			if (accentId == null) {
				if (other.accentId != null)
					return false;
			} else if (!accentId.equals(other.accentId))
				return false;
			if (ancodeId == null) {
				if (other.ancodeId != null)
					return false;
			} else if (!ancodeId.equals(other.ancodeId))
				return false;
			if (flexiaId == null) {
				if (other.flexiaId != null)
					return false;
			} else if (!flexiaId.equals(other.flexiaId))
				return false;
			if (prefixId == null) {
				if (other.prefixId != null)
					return false;
			} else if (!prefixId.equals(other.prefixId))
				return false;
			return true;
		}
		private AotInMemoryDao getOuterType() {
			return AotInMemoryDao.this;
		}
		
	}

	@Override
	public Set<String> getPrefixes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<MorphologyAnalysis> findWordByFlexia(String lemma, String flexia) throws AotException {
		// TODO Auto-generated method stub
		return null;
	}
}
