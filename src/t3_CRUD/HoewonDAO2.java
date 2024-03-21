package t3_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class HoewonDAO2 { //데이터베이스 관리
	
	private Connection conn = null;
	
	public HoewonDAO2() {
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
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO>  vos = new ArrayList<HoewonVO>();
		try {
			Statement stmt  = conn.createStatement();
			String sql = "select * from hoewon";
			ResultSet rs = stmt.executeQuery(sql);
			
			HoewonVO vo = null;
			while(rs.next()) {
				vo = new HoewonVO();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getNString("gender"));
				vo.setAddress(rs.getString("address"));
				
				vos.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} //table 제어 준비 끝
		return vos; //select - 결과값을 돌려 받는다 :: excuteQuery
	}
	
	
	//개별 조회
	public HoewonVO getSearch(String name) {
		HoewonVO vo = new HoewonVO();
		
		try {
			Statement stmt  = conn.createStatement();
			String sql = "select * from hoewon where name = '"+name+"'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getNString("gender"));
				vo.setAddress(rs.getString("address"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} //table 제어 준비 끝
		
		return vo;
	}
	
	
	//회원 자료 수정
	//System.out.print("수정할 항목을 입력하세요~!  1.성명		2.나이		3.성별		4.주소 ==>  ");
	public void setUpdate(int idx, int choice, String content) {
		try {
			Statement stmt  = conn.createStatement();
			String sql = "";
			
			if(choice  == 1) {
				sql = "update hoewon set name = '"+content+"' where idx = " + idx; 
			}
			else if(choice == 2) {
				sql = "update hoewon set age = "+Integer.parseInt(content)+" where idx = " + idx; //나이 - int 기 때문에 '' 삭제, Integer.parseInt
			}
			else if(choice == 3) {
				sql = "update hoewon set gender = '"+content+"' where idx = " + idx;
			}
			else if(choice == 4) {
				sql = "update hoewon set address = '"+content+"' where idx = " + idx;
			}
			stmt.executeUpdate(sql); //select 빼고 나머지는 executeUpdate
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}

	
	//회원 삭제 처리
	public void setDelete(String name) {
		try {
			Statement stmt  = conn.createStatement();
			String sql = "delete from hoewon where name = '"+name+"'";
			stmt.executeUpdate(sql); //select 빼고 나머지는 executeUpdate
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		}
	}
	
}