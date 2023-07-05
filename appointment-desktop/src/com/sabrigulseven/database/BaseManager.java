package com.sabrigulseven.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseManager {
	
	private String url="jdbc:mysql://localhost:3306/appointment?useLegacyDatetimeCode=false&serverTimezone=Turkey";
	private String user="root";
	private String password="godoro";
	private String driver="com.mysql.cj.jdbc.Driver";
	
	protected Connection connection;
	
	public BaseManager() {
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected void connect() throws SQLException{
		 connection = DriverManager.getConnection(url,user,password);
	}
	protected void disconnect() throws SQLException{
		if(connection != null) {
			connection.close();	
		}
	}
}

