package t1_Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Test1DAO {
	
	public Test1DAO() {
		
		try {
			//1. JDBC 드라이버 검색
			Class.forName("com.mysql.jdbc.Driver"); //외우기
			System.out.println("드라이버 검색 성공 훠우~!~!~!");
			
			//2. DB 서버에 연결(연동)후 데이터베이스 사용준비 작업
			String url = "jdbc:mysql://localhost:3306/javaclass"; //외우기
			String user = "atom"; //cmd mysql -u atom -p 로그인, show databases;
			String pwd = "1234";
			DriverManager.getConnection(url, user, pwd); //중첩예외
			System.out.println("데이터 베이스 연결 성공 훠우~!~!~!");
			//빨간 글자 : 데이터 베이스 서버에 접속했다는걸 알려줌, 오류 아님!!!
		} catch (ClassNotFoundException e) {
			System.out.println("!!!드라이버 검색 실패 :: Driver 글자 확인, jdbc 확인");
		} //-- 예외처리
			catch (SQLException e) { //--중첩 예외처리
			System.out.println("!!!사이트 접속 실패/데이터베이스 연결 실패 :: url, id, pwd 확인");
		} finally {
			System.out.println("작업 끝~!~!~! 훠우~!~!~!~!");
		}
		
	}
	
	
}
