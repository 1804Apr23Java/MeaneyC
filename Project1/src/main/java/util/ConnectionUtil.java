package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
	
	private static String filename = "connection.properties";
	
	public static Connection getConnectionFromFile() throws IOException, SQLException {
		
		//check that driver is being seen by Maven
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e){
			e.printStackTrace();
		}
		//load connection credentials from file
		//Properties prop = new Properties();
		/*InputStream in = new FileInputStream(filename);
		prop.load(in);*/
		//ClassLoader loader = Thread.currentThread().getContextClassLoader();
		//prop.load(loader.getResourceAsStream(filename));
		//String url = prop.getProperty("url");
		//String username = prop.getProperty("username");
		//String password = prop.getProperty("password");
		//return DriverManager.getConnection(url,username,password);
		return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "Project1", "p4ssw0rd");
	}

}