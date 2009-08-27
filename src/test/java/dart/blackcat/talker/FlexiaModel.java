package dart.blackcat.talker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:context.xml"})
public class FlexiaModel {
	
	@Autowired
	private DataSource dataSource;

	@Test
	@Transactional
	public void main() throws IOException {
		
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		String path = "c:\\Users\\pvyazankin\\projects\\morph-rus\\Dicts\\SrcMorph\\RusSrc\\flexiam.tmp";
		FileReader fileReader = new FileReader(path);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		String line = null;
		int lineNumber = 0;
		int formNo = 0;
		String flexia = null;
		
		while ((line = bufferedReader.readLine()) != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(line, "%");
			
			while (stringTokenizer.hasMoreTokens()) {
				String token = stringTokenizer.nextToken();
				
				int a = token.indexOf("*");
				flexia = new String(token.toCharArray(), 0, a);
//				char[] chars = new String(token.toCharArray(), a + 1, token.length() - 1 - a).toCharArray();
//				int[] ints = {chars[0] << 16, chars[1]};
//				System.out.println(Arrays.toString(chars));
//				System.out.println(Integer.toBinaryString(ints[0]));
//				System.out.println(Integer.toBinaryString(ints[1]));
//				formNo = ints[1];
				System.out.println("ID:" + lineNumber + " flexia:" + flexia + " formNo:" + formNo);
				
				try {
					jdbcTemplate.update(
							"insert into \"FlexiaModels\" (\"FlexiaModelId\", \"FormNo\", \"FlexiaStr\") values (?,?,?)", 
							new Object[] {lineNumber, formNo, flexia}
					);
				} catch (DataAccessException e) {
					e.printStackTrace();
				}
				
				formNo++;
			}
			
			formNo = 0;
			lineNumber++;
		}
	}
}
