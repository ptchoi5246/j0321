package t1_Connection;

//import java.sql.*; 이렇게도 사용 가능하다

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Test2DAO {
	Connection conn = null; 
	//Connection : java.sql 사용하기
	//여기저기서 conn 호출할 수 있게 필드에 선언하기
	
	public Test2DAO() {
		try {
			//1. JDBC 드라이버 검색
			Class.forName("com.mysql.jdbc.Driver"); //외우기
			System.out.println("드라이버 검색 성공 훠우~!~!~!");
			
			//2. DB 서버에 연결(연동)후 데이터베이스 사용준비 작업
			String url = "jdbc:mysql://localhost:3306/javaclass"; //외우기
			String user = "atom"; //cmd mysql -u atom -p 로그인, show databases;
			String pwd = "1234";
			conn = DriverManager.getConnection(url, user, pwd); //중첩예외
			System.out.println("데이터 베이스 연결 성공 훠우~!~!~!");
			//빨간 글자 : 데이터 베이스 서버에 접속했다는걸 알려줌, 오류 아님!!!
		} catch (ClassNotFoundException e) {
			System.out.println("!!!드라이버 검색 실패 :: Driver 글자 확인, jdbc 확인");
		} //-- 예외처리
			catch (SQLException e) { //--중첩 예외처리
			System.out.println("!!!사이트 접속 실패/데이터베이스 연결 실패 :: url, id, pwd 확인");
		} finally {
//			try {
//				conn.close(); //--예외처리
//				System.out.println("데이터 베이스 연결 종료 성공 훠오~!~!~!");
//			} catch (SQLException e) {} //에러날 일 거의 없음
			//생성자 안에서 conn.close() 하면 안됨!!! 생성자가 열자마자 바로 닫음
//			connClose(); //의미없음
			System.out.println("작업 끝~!~!~! 훠우~!~!~!~!");
		}
	}
	
	//conn객체 close //객체 무조건 따로 빼놔야된다!!!!!
	public void connClose() {
		try {
			conn.close();
			System.out.println("데이터 베이스 연결 종료 성공 훠오~!~!~!");
		} catch (SQLException e) {	}
	}
}
