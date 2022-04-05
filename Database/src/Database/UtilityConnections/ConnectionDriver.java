package UtilityConnections;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDriver {
	
	protected static Connection getConnection() throws Exception {
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://35.225.205.120:3306/application_db";
			String username = "ricc-application-db";
			String password = "userpass";
			
			Class.forName(driver);
			
			Connection con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	protected static void getFileConnection() { //finish later. 
		
	}
}


