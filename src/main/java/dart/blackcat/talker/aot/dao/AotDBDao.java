package dart.blackcat.talker.aot.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import dart.blackcat.talker.aot.AotException;
import dart.blackcat.talker.morph.MorphologyAnalysis;
import dart.blackcat.talker.util.StringUtils;

public class AotDBDao extends AbstractAotDao {
	
	protected static final String FIND_WORD = 
		"select 							\n" +
		"		base_str,					\n" +
		"		flexia_str,					\n" +
		"		accent_char_no,				\n" +
		"		a.path_of_speech,			\n" +
		"		a0.path_of_speech,			\n" +
		"		a.grammems,					\n" +
		"		a0.grammems					\n" +
		"from								\n" +
		"	lemmata l						\n" +
		"	right join flexia_model fm on l.flexia_model_id = fm.flexia_model_id\n" +
		"	left join accent_model am on (	\n" +
		"		l.accent_model_id = am.accent_model_id and\n" +
		"		fm.form_no = am.form_no		\n" +
		"	)								\n" +
		"	left join ancode a on a.ancode = l.ancode		\n" +
		"	left join ancode a0 on a0.ancode = fm.ancode	\n" +
		"where	l.base_str = ? and fm.flexia_str = ?		\n";
	
	protected static final String FIND_BY_FLEXIA = 
		"SELECT 							\n" +
		"	'', 							\n" +
		"	'', 							\n" +
		"	NULL, 							\n" +
		"	NULL, 							\n" +
		"	a.path_of_speech, 				\n" +
		"	'', 							\n" +
		"	a.grammems 						\n" +
		"FROM  								\n" +
		"	flexia_model fm 				\n" +
		"	LEFT JOIN ancode a ON fm.ancode = a.ancode		\n" +
		"WHERE 								\n" +
		"	fm.flexia_str = ? --and a.path_of_speech = 'С' 	\n";
	
	protected AotResultSetExtractor rse = new AotResultSetExtractor();

	/**
	 * find morphology information by lemma
	 * @param lemma lemma (immutable word part). must be in UPPER CASE.
	 * @return {@link HashSet}, can be empty
	 * @throws AotException if there is any problem
	 */
	@SuppressWarnings("unchecked")
	public Set<MorphologyAnalysis> findWord(final String lemma, final String flexia) throws AotException {
		try {
			Set<MorphologyAnalysis> result = (Set<MorphologyAnalysis>) getJdbcTemplate().query(FIND_WORD, new PreparedStatementSetter() {
	
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, lemma);
					ps.setString(2, flexia);
				}
				
			}, rse);
			
			return result;
		} catch (DataAccessException e) {
			throw new AotException(e);
		}
	}	
	
	
	protected class AotResultSetExtractor implements ResultSetExtractor {

		@Override
		public Object extractData(ResultSet rs) throws SQLException, DataAccessException {
			Set<MorphologyAnalysis> result = new HashSet<MorphologyAnalysis>();
			
			int k = 0;
			while (rs.next()) {
				String base = StringUtils.emptyIfNull(rs.getString(1));
				String flexia = StringUtils.emptyIfNull(rs.getString(2));
				byte accentCharNo = rs.getByte(3);
				String pathOfSpeech = rs.getString(4);
				String pathOfSpeech0 = rs.getString(5);
				String grammemasString = rs.getString(6);
				String grammemas0String = rs.getString(7);
				
				if (pathOfSpeech != null) {
					if ( ! pathOfSpeech.equals(pathOfSpeech0)) {
						throw new SQLException("Path of speech conflict! " + pathOfSpeech + " vs. " + pathOfSpeech0 + " Base=" + base + ", flexia=" + flexia);
					}
				}
				
				StringTokenizer st = new StringTokenizer(grammemasString == null ? "" : grammemasString, ",");
				StringTokenizer st0 = new StringTokenizer(grammemas0String == null ? "" : grammemas0String, ",");
//				Grammema[] grammemas = new Grammema[st.countTokens() + st0.countTokens()];
				long grammemas = 0;
//				int i = 0;

				try {
					while (st.hasMoreTokens()) {
//						grammemas[i] = string2Grammema(st.nextToken());
						grammemas |= string2Grammema(st.nextToken());
//						i++;
					}
					while (st0.hasMoreTokens()) {
//						grammemas[i] = string2Grammema(st0.nextToken());
						grammemas |= string2Grammema(st0.nextToken());
//						i++;
					}
				
					result.add(new MorphologyAnalysis("", base, flexia, accentCharNo, string2PathOfSpeech(pathOfSpeech0), grammemas));
				} catch (DatabaseIntegrityViolationException e) {
					throw new SQLException("Something wrong with DB dictionary. Base=" + base + ", flexia=" + flexia, e);
				}
				k++;
			}
			
			return result;
		}
		

	}


	@SuppressWarnings("unchecked")
	@Override
	public Set<String> getPrefixes() throws DataAccessException {
		Set<String> result = new HashSet<String>();
		result.addAll(getJdbcTemplate().queryForList("SELECT prefix_str FROM prefix_set;", String.class));
		return result;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Set<MorphologyAnalysis> findWordByFlexia(String lemma, final String flexia) throws AotException {
		try {
			Set<MorphologyAnalysis> result = (Set<MorphologyAnalysis>) getJdbcTemplate().query(FIND_BY_FLEXIA, new PreparedStatementSetter() {
	
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, flexia);
				}
				
			}, rse);
			
			for (Iterator<MorphologyAnalysis> i = result.iterator(); i.hasNext();) {
				MorphologyAnalysis morphologyAnalysis = i.next();
				morphologyAnalysis.setBase(lemma);
				morphologyAnalysis.setFlexia(flexia);
			}
			
			return result;
		} catch (DataAccessException e) {
			throw new AotException(e);
		}
	}
}
