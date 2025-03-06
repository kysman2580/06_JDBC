package com.kh.mvc2.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;
import com.kh.mvc2.model.dto.UserDTO2;
import com.kh.mvc2.util.JdbcUtil2;


/**
 * DAO(Data Access Object)
 * 
 * 데이터베이스 관련된 작업(CRUD)을 전문적으로 담당하는 객체 DAO안에 모든 메소드들은 데이터베이스와 관련된 기능으로 만들 것
 * 
 * Controller를 통해 호출된 기능을 수행 DB에 직접 접근한 후 SQL문을 수행하고 결과 받기(JDBC)
 */
public class UserDAO2 {

	private final String URL = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
	private final String USERNAME = "KH07_KYS";
	private final String PASSWORD = "KH1234";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ojdbc.. 잘 넣었나요?" + "\n오타 안 났나요?");
		}
	}
	
	public List<UserDTO2> findAll(Connection conn) {
		
		List<UserDTO2> list = new ArrayList<UserDTO2>();
		
		String sql = """
				SELECT EMP_ID, EMP_NAME, EMP_NO, EMAIL, PHONE
				FROM EMPLOYEE
				ORDER BY EMP_ID ASC
				""";
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
		pstmt = conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		
		while(rs.next()) {
			UserDTO2 user2 = new UserDTO2();
			user2.setEmpId(rs.getString("EMP_ID"));
			user2.setEmpName(rs.getString("EMP_NAME"));
			user2.setEmpNo(rs.getString("EMP_NO"));
			user2.setEmail(rs.getString("EMAIL"));
			user2.setPhone(rs.getString("PHONE"));

			list.add(user2);
		}
		
		} catch (SQLException e) {
			System.out.println("오타가 나지 않았나요? 확인하셨나요? 두 번 봤나요?");
		}

		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			System.out.println("ㅁㄹ db서버 이상해");
		}

		JdbcUtil.close(pstmt);

		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			System.out.println("conn 이상해");
		}

		return list;
	}

	
	public int insertUser(String name, String email, String phone) {
		Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = "INSERT INTO EMPLOYEE (EMP_NAME, EMAIL, PHONE) VALUES (?, ?, ?)";

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return result;
    }

	
	
	public int updateName(String oldName, String newName) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = """
				UPDATE EMPLOYEE
				SET EMP_NAME = ?
				WHERE EMP_NAME = ?
					 """;

		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, newName);
			pstmt.setString(2, oldName);

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil2.close(pstmt);
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}


	public int deleteUser(String name) {
		
		
		Connection conn = null;
        PreparedStatement pstmt = null;
        int result = 0;

        String sql = """
        		DELETE 
        		FROM EMPLOYEE 
        		WHERE EMP_NAME = ?
        		""";

        try {
            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(pstmt);
            JdbcUtil.close(conn);
        }

        return result;
	}

	
	





}
