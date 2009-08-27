package dart.blackcat.talker.ru.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.sql.DataSource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;

public class DictImporter {
	
	private static JdbcTemplate jdbcTemplate;
	private static BufferedReader bufferedReader;

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"context.xml"});
		BeanFactory factory = context;
		
		DataSource dataSource = (DataSource) factory.getBean("dataSource");
		PlatformTransactionManager txManager = (PlatformTransactionManager) factory.getBean("transactionManager");
		jdbcTemplate = new JdbcTemplate(dataSource);
		
		FileReader fileReader;
		
		//*
		
		if ( ! args[1].isEmpty()) {
		
			try {
				fileReader = new FileReader(args[1]);
				bufferedReader = new BufferedReader(fileReader);
				
				TransactionStatus txStatus = txManager.getTransaction(null);
				if (args[0].equalsIgnoreCase("ancode")) {
					ancode();
				} else if (args[0].equalsIgnoreCase("flexia")) {
					flexia();
				} else if (args[0].equalsIgnoreCase("prefix")) {
					prefix();
				} else if (args[0].equalsIgnoreCase("accent")) {
					accent();
				} else {
					usage();
				}
				txManager.commit(txStatus);
				return;
			} catch (FileNotFoundException e) {
				System.out.println("File not found: " + args[1]);
				return;
			}
		}
		usage();
	}
	
	private static void usage() {
		System.out.println("Usage: ancode|flexia|prefix|accent <path-to-file>");
	}
	
	public static void ancode() throws IOException {
		String line = null;
		int lineNumber = 0;
		String ancode = null;
		String xpeHb = null;
		String pathOfSpeech = null;
		String grammems = null;
		int i  = 0;
		
		while ((line = bufferedReader.readLine()) != null) {
			if ( ! line.isEmpty() &&  ! line.startsWith("//")) {
				
				StringTokenizer stringTokenizer = new StringTokenizer(line, " ");
				while (stringTokenizer.hasMoreTokens()) {
					switch (i) {
					case 0:
						ancode = stringTokenizer.nextToken();
						break;
					case 1:
						xpeHb = stringTokenizer.nextToken();
						break;
					case 2:
						pathOfSpeech = stringTokenizer.nextToken();
						break;
					case 3:
						grammems = stringTokenizer.nextToken();
						break;
					
					default:
						throw new RuntimeException("oops!");
					}
					i++;
				}
				i = 0;
				
				System.out.println("ancode:" + ancode + " xpeHb:" + xpeHb + " pathOfSpeech:" + pathOfSpeech + " grammems:" + grammems);
				
				try {
					jdbcTemplate.update(
							"insert into ancode values (?,?,?)", 
							new Object[] {ancode, pathOfSpeech, grammems}
					);
				} catch (DataAccessException e) {
					e.printStackTrace();
					throw e;
				}
				
				lineNumber++;	
			}
		}
	}
	
	public static void prefix() throws IOException {
		String line = null;
		int lineNumber = 0;
		
		while ((line = bufferedReader.readLine()) != null) {
			System.out.println("ID:" + lineNumber + " prefix:" + line);
			
			try {
				jdbcTemplate.update(
						"insert into prefix_set values (?,?)", 
						new Object[] {lineNumber, line}
				);
			} catch (DataAccessException e) {
				e.printStackTrace();
			}
			
			lineNumber++;
		}
	}
	
	public static void accent() throws IOException {
		String line = null;
		int lineNumber = 0;
		int formNo = 0;
		short accentCharNo = 0;
		
		while ((line = bufferedReader.readLine()) != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(line, ";");
			
			while (stringTokenizer.hasMoreTokens()) {
				accentCharNo = Short.valueOf(stringTokenizer.nextToken());
				
				System.out.println("ID:" + lineNumber + " formNo:" + formNo + " accentCharNo:" + accentCharNo);
				
				try {
					jdbcTemplate.update(
							"insert into accent_model values (?,?,?)", 
							new Object[] {lineNumber, formNo, accentCharNo}
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
	
	public static void flexia() throws IOException {
		String line = null;
		int lineNumber = 0;
		int formNo = 0;
		String flexia = null;
		String ancode = null;
		
		while ((line = bufferedReader.readLine()) != null) {
			StringTokenizer stringTokenizer = new StringTokenizer(line, "%");
			
			while (stringTokenizer.hasMoreTokens()) {
				String token = stringTokenizer.nextToken();
				
				int a = token.indexOf("*");
				flexia = new String(token.toCharArray(), 0, a);
				ancode = new String(token.toCharArray(), a + 1, 2);
				
				System.out.println("ID:" + lineNumber + " formNo:" + formNo + " flexia:" + flexia + " ancode:" + ancode);
				
				try {
					jdbcTemplate.update(
							"insert into flexia_model values (?,?,?,?,?)", 
							new Object[] {lineNumber, formNo, "", flexia, ancode}
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
