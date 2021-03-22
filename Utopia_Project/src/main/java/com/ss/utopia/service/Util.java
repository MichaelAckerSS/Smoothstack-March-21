package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
	
	private static final String SQL_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String SQL_URL = "jdbc:mysql://localhost:3306/utopia";
	private static final String SQL_USERNAME = "utopia";
	private static final String SQL_PASSWORD = "password";

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(SQL_DRIVER);
		Connection conn = DriverManager.getConnection(SQL_URL, SQL_USERNAME, SQL_PASSWORD);
		conn.setAutoCommit(Boolean.FALSE);
		return conn;
	}
}