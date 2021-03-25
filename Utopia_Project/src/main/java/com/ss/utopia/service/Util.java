package com.ss.utopia.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
	
	String alphaNum = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	
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
	
	public String genConfCode() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i<8; i++) {
			int index = (int) (alphaNum.length() * Math.random());
			sb.append(alphaNum.charAt(index));
		}
		//Note: Math.random will not return 1.0, so 0 will not be included. This is for the best, so it is not confused with O.
				//Another note: with 35^8 = over 2.2 trillion combinations, checking for duplicate codes is not necessary.
		return sb.toString();
	}
}