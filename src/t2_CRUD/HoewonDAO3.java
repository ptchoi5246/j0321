package t2_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class HoewonDAO3 {
	
	private Connection conn = null;
	
	public HoewonDAO3() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost:3306/javaclass";
			String user = "atom";
			String pwd = "1234";
			conn = DriverManager.getConnection(url, user, pwd);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		}
	}
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}

	//Hoewon 전체 조회
	public void getList() {
		try {
			Statement stmt  = conn.createStatement();
			String sql = "select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			// stmt로 관리하는 record를 부르는 명령 Query
			
			
			System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
			System.out.println("번호\t성명\t나이\t성별\t주소");
			System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
			while(rs.next()) { //자료를 보고 있는 rs를 내리기 위해서 .next();
				System.out.print(rs.getInt("idx") + "\t"); //테이블에 있는 필드
				System.out.print(rs.getString("name") + "\t");
				System.out.print(rs.getInt("age") + "\t");
				System.out.print(rs.getString("gender") + "\t");
				System.out.print(rs.getString("address") + "\n");
				System.out.println();
			}
			System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} //table 제어 준비 끝
		
	}
}
