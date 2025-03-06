package example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
	public static void main(String[] args) {
		
		
		/* java.sql.PreaparedStatement(준비된 Statement)
		 * 
		 * - 전달하려는 SQL 중간에 ?(placeholder)를 작성하여
		 * 	? 자리에 java값을 대입할 준비가 되어있는 Statement
		 * 
		 * 장점 1 : 값 추가가 간단해짐
		 * 
		 * 장점2 : ?값 대입 시 지 자료형에 맞는 리터롤 표기법으로 자동 변환된다!
		 * 
		 * ex) ?에 int 대입 -> NUMBER 타입 리터럴(123)
		 * ex) ?에 String 대입 -> CHAR 타입 리터럴('123')
		 * 							(양쪽에 '' 자동 추가)
		 * 
		 * 장점 3 : 
		 * Statement의 문제점인 SQL Injection을 방지할 수 있다.
		 * -> Statement는 문자열 결합 방식으로 SQL을 만듦
		 *  -> 결합되는 문자열이 SQL 명령어 또는 '값' 모두 가능
		 *  
		 *  
		 *  PreparedStatement는
		 *  ?를 이용해서 파라미터(값)만 연결 하기 때문에
		 *  SQL Injection을 방지할 수 있다.
		 * 
		 */
		Connection conn = null;
		PreparedStatement pstmt = null;
			
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			// 해당 클래스 읽어서 메모리에 적재함(로드)

			// 내 컴퓨터 DB 연결시
			// jdbc:oracle:thin:@localhost:1521:xe
			// - jdbc 드라이버가 어떤 데이터베이스에 연결할지 지정
			String url = "jdbc:oracle:thin:@112.221.156.34:12345:XE";
			String userName = "KH07_KYS";	// 사용자 계정명
			String password = "KH1234";		// 계정 비밀번호
			
			conn = DriverManager.getConnection(url, userName, password);
			
			
			Scanner sc = new Scanner(System.in);
			System.out.print("아이디 입력 : ");
			String id = sc.next();
			
			System.out.print("비밀번호 입력 : ");
			String pw = sc.next();
			
			System.out.print("이름 입력 : ");
			String name = sc.next();
			
	
			String sql = 
					"INSERT INTO TB_USER VALUES(SEQ_USER_NO.NEXTVAL,?, ?, ?, DEFAULT) ";
			
			/* DML(INSERT, UPDATE, DELETE)시 AutoCommit 끄기 
			 * -> Connection을 생성하면 기본적으로 true(켜져있음)
			 * -> 매번 수동으로 끔
			 * 	왜? 개발자가 트랜잭션을 마음대로 제어하기 위해서
			 * */
			
			conn.setAutoCommit(false);
			
			
			/* PreparedStatement 객체 생성 */
			// -> 객체 생성과 동시 SQL을 매개 변수로 전달해서 담아둠
			// -> SQL 파악하기위해서
			pstmt = conn.prepareStatement(sql);
			
			/* ?(placeholder)에 알맞은 값 세팅 */
			// pstmt.set자료형(?순서, 대입할값)
			
			// -- 바인딩 과정 -- 
			pstmt.setString(1, id); // id 변수 값에 '' 추가하여 sql 대입
			pstmt.setString(2, pw);
			pstmt.setString(3, name);
			
			/* SQL 수행 후 결과 반환 받기
			 * - SELECT : executeQuery() -> ResultSet 반환
			 * - DML 	: executeUpdate() -> 행의 개수(int) 반환
			 * 
			 * */
			//
			int result = pstmt.executeUpdate();
			
			
			/* result 값에 따라서 SQL 성공/실패 여부 출력
			 * + 트랜잭션 제어(commit/rollback)
			 */
			
			if(result > 0) {
				System.out.println(id + "님이 추가 되었습니다.");
				conn.commit(); // COMMIT 수행 -> INSERT 내역 DB 반영
			}else {
				System.out.println("추가 실패!");
				conn.rollback();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			// JDBC 객체 자원 반환
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}












