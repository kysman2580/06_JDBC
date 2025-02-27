package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample5 {
	public static void main(String[] args) {
		// 부서명을 입력 받아
		// 해당 부서의 근무하는 사원의
		// 사번, 이름, 부서명, 직금명을
		// 직급코드 내림 차순으로 조회
		
		
		Scanner sc = new Scanner(System.in);
		// 1. JDBC 객체 참조 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		

		// 2. DriverManager 객체를 이용해 Connection 객체 생성
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			String type = "jdbc:oracle:thin:@";	
			String host = "112.221.156.34";			
			String port = ":12345";			
			String dbName = ":XE";			
			String userName = "KH07_KYS";			
			String password = "KH1234";
			
			
			conn = DriverManager.getConnection(
					type + host + port +dbName, 
					userName, 
					password
					);
			
			System.out.println("================");
			System.out.print("부서명 입력 : ");
			String deptTitle = sc.nextLine();
			

		// 3. SQL 작성
			StringBuilder sb = new StringBuilder();
			sb.append("SELECT E.EMP_ID, E.EMP_NAME, D.DEPT_TITLE, J.JOB_NAME ");
			sb.append("FROM EMPLOYEE e ");
			sb.append("JOIN DEPARTMENT d ON (e.DEPT_CODE = d.DEPT_ID) ");
			sb.append("JOIN job j ON (e.JOB_CODE = j.JOB_CODE) " );
			sb.append("WHERE DEPT_TITLE = ");
			sb.append("'");
			sb.append(deptTitle);
			sb.append("'");
			sb.append(" ORDER BY e.JOB_CODE ASC");
			
			
			String sql = sb.toString();
		 
		// 4. sql을 전달하고 결과를 받아올 Statement 객체 생성
			
			stmt = conn.createStatement();
		 
		// 5. Statement 객체를 이용해서SQL을 DB로 전달 후 수행
		// 1) SELECT문 : executeQuery() -> ResultSet으로 반환 
		// 2) DML문 	 : executeUpdate() -> 결과 행의 개수(int) 반환
		 
			rs = stmt.executeQuery(sql);
			
			
		// (5번 SQL이 SELECT인 경우에만)
		// 6. 조회 결과가 저장된 ResultSet을 1행씩 접근하여 각 행에 기록된 컬럼 값 얻어오기

			System.out.println("사번 | 이름 | 부서명 | 직급명");
			System.out.println("----------------------------");
			
			while(rs.next()) {
				
				String emp_id = rs.getString("EMP_ID");
				String emp_Name = rs.getString("EMP_NAME");
				String dept_Name = rs.getString("DEPT_TITLE");
				String job_Name = rs.getString("JOB_NAME");
				
				System.out.printf("%s | %s | %s | %s \n", emp_id, emp_Name, dept_Name, job_Name);
			}
			
			
			
			
		// 7. 사용 완료된 JDBC 객체 자원 반환



		
		
		}catch (SQLException e) {
			e.printStackTrace();
			
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				if(conn != null) conn.close();
				if(stmt != null) stmt.close();
				if(rs != null) rs.close();	
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}













