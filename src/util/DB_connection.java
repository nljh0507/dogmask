package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DB_connection {
	public static Connection getConnection() {
		Connection conn = null;
		
		try {
			String jdbcURL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8";
			String dbID = "test";
			String dbPass = "dlgud12";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(jdbcURL, dbID, dbPass);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
