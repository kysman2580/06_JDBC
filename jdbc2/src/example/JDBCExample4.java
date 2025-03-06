package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {

		// 아이디, 비밀번호, 새 비밀번호를 입력 받아
		// 아이디, 비밀번호가 일치하는 회원의 비밀번호 변경
		
		// 1. JDBC 객체 참조 변수 선언
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			
			// 2. DriverManager 객체를 이용해 Connection 객체 생성
			Class.forName("oracle.jdbc.OracleDriver");
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH07_KYS"; // 사용자 계정명
			String password = "KH1234"; // 계정 비밀번호
			conn = DriverManager.getConnection(url, userName, password);
			
			conn.setAutoCommit(false);
			
			Scanner sc = new Scanner(System.in);
			System.out.print("아이디 입력 : ");
			String id = sc.next();
			
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.next();
			
			
			System.out.print("새 비밀번호 입력 : ");
			String npw = sc.next();
			
		

		// 3. SQL 작성
			String sql = """
					UPDATE TB_USER SET
					USER_PW = ?
					WHERE
					 USER_ID = ?
					AND
					 USER_PW = ?
					""";
		 
			// 4. sql을 전달하고 결과를 받아올
			// PreparedStatement 객체 생성 + ? 값 세팅
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, npw);
			pstmt.setString(2, id);
			pstmt.setString(3, pw);
			
		 
		// 5. Statement 객체를 이용해서SQL을 DB로 전달 후 수행
		// 1) SELECT문 : executeQuery() -> ResultSet으로 반환 
		// 2) DML문 	 : executeUpdate() -> 결과 행의 개수(int) 반환
			
			int result = pstmt.executeUpdate();
			
		// (5번 SQL이 SELECT인 경우에만)
		// 6. 조회 결과가 저장된 ResultSet을 1행씩 접근하여 각 행에 기록된 컬럼 값 얻어오기
			if(result > 0) {
				System.out.println("비밀번호가 변경되었습니다.");
				conn.commit();
			}else {
				System.out.println("아이디/비밀번호가 일치하지 않습니다.");
				conn.rollback();
			}
			

		// 7. 사용 완료된 JDBC 객체 자원 반환
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)pstmt.close();
				if(conn != null)conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		
	}
}
