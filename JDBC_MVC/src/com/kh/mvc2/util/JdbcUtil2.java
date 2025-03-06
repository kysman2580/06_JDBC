package com.kh.mvc2.util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcUtil2 {

	/**
	 * JDBC API 사용 중 중복 코드가 너무 많아
	 * 중복된 코드를 메소드로 분리하여 필요할 때 마다 
	 * '재사용' 하기 위함
	 */
	
	public static Connection getConnection() {
		
		final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
		final String USERNAME = "KH07_KYS";
		final String PASSWORD = "KH1234";
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	
	public static void close(PreparedStatement pstmt) {
		try {
			if(pstmt != null) pstmt.close();
		}catch (SQLException e) {
			System.out.println("pstmt 오류");
		}
	}
	
	
	
}
