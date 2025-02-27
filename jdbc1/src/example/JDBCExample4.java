package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample4 {
	public static void main(String[] args) {
		
		// 최소, 최대 급여 범위를 입력 받아
		
		// EMPLOYEE 테이블에서
		// 급여 "최소" 이상 "최대" 이하로받는 사원의
		// 사번, 이름, 부서코드, 급여를
		// 급여 내림차순으로 출력
		
		/**
		 * SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY
		 * FROM EMPLOYEE
		 * WHERE SALARY BETWEEN 3000000 AND 5000000
		 * ORDER BY SALARY ASC
		 */
		Scanner sc = new Scanner(System.in);
		
		// 1. JDBC 객체 참조 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		// 2. DriverManager 객체를 이용해 Connection 객체 생성
		try {
			
		// Oracle JDBC Driver를 메모리에 로드(적재) == 객체로 만듬
		Class.forName("oracle.jdbc.OracleDriver");
		
		// DB 저장 정보
		String type = "jdbc:oracle:thin:@";	
		String host = "112.221.156.34";			
		String port = ":12345";			
		String dbName = ":XE";			
		String userName = "KH07_KYS";			
		String password = "KH1234";
		
		
		conn = DriverManager.getConnection(
				type + host + port + dbName, 
				userName, 
				password
				);

		
		
		// 범위 입력 받기
		System.out.println("=== 범위 내 급여 받는 직원 조회 ===");
		System.out.print("최소 값 입력 : ");
		int min = sc.nextInt();
		
		System.out.print("최대 값 입력 : ");
		int max = sc.nextInt();

		
		
		
		// 3. SQL 작성
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT EMP_ID, EMP_NAME, DEPT_CODE, SALARY ");
		sb.append("FROM EMPLOYEE ");
		sb.append("WHERE SALARY BETWEEN ");
		sb.append(min);
		sb.append(" AND ");
		sb.append(max);
		sb.append(" ORDER BY SALARY ASC ");
		String sql = sb.toString();
		 
		// 4. sql을 전달하고 결과를 받아올 Statement 객체 생성
		stmt = conn.createStatement();
		
		 
		// 5. Statement 객체를 이용해서SQL을 DB로 전달 후 수행
		// 1) SELECT문 : executeQuery() -> ResultSet으로 반환 
		// 2) DML문 	 : executeUpdate() -> 결과 행의 개수(int) 반환
		rs = stmt.executeQuery(sql);
		
		 
		// (5번 SQL이 SELECT인 경우에만)
		// 6. 조회 결과가 저장된 ResultSet을 1행씩 접근하여 각 행에 기록된 컬럼 값 얻어오기
		while(rs.next()) {
			
			String emp_Id = rs.getString("EMP_ID");
			String emp_Name = rs.getString("EMP_NAME");
			String dept_Title = rs.getString("DEPT_CODE");
			int salary = rs.getInt("SALARY");
			
			System.out.printf("%s | %s | %s | %d \n", 
						emp_Id, emp_Name, dept_Title, salary
					);
		}

		// 7. 사용 완료된 JDBC 객체 자원 반환

		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				
				if(conn != null)conn.close();
				if(stmt != null)stmt.close();
				if(rs != null)rs.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}


		
	}
}










