package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.sql.Connection;

public class DBUtil {
	private static Connection cnn = null;
	public static Connection getConnection() {
		if(cnn!=null) {
			return cnn;
		}
		else {
			try {
				Properties prop = new Properties();
				InputStream input = new FileInputStream("db.properties");
				prop.load(input);
				String driver = prop.getProperty("driver");     	//"com.mysql.jdbc.Driver";
				String url = prop.getProperty("url");				//"jdbc:mysql://localhost:3306/data";
				String user =  prop.getProperty("user");			//"root";
				String password = prop.getProperty("password"); 	//"";
				System.out.println(driver+" "+url);
				Class.forName(driver);
				cnn = DriverManager.getConnection(url, user, password);
			}
			catch(ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			catch(IOException e) {
				e.printStackTrace();
			}
			return cnn;
		}
	}

}
