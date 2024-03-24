package t5_CRUD_2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class SungjukDAO2 {
	
	Connection conn = null; //java.sql
	PreparedStatement pstmt = null;
	ResultSet rs = null; //java.sql
	
	SungjukVO2 vo = null;
	String sql = "";
	
	public SungjukDAO2() {

		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String pwd = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 : " + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 실패 : " + e.getMessage());
		}
	}
	
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	public void pstmtClose() {
		if(pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {}
	}
	
	public void rsClose() {
		if(rs != null)
			try {
				rs.close();
				pstmtClose();
			} catch (SQLException e) {}
	}
	
	
	//전체 성적 조회
	public ArrayList<SungjukVO2> getSungjukList() {
		ArrayList<SungjukVO2> vos = new ArrayList<SungjukVO2>();
		
		try {
			sql = "select * from sunjuk order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
				vo = new SungjukVO2();
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
				
				vos.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vos;
		
	}
	
	//성적 개별 조회
	public SungjukVO2 getSungjukSearch(String name) {
		SungjukVO2 vo = new SungjukVO2();
		
		try {
			sql = "select * from sungjuk where name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat"));
			}
			else vo = null;
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose();
		}
		return vo;
	}

	//성적 입력
	public int setSungjukInput(SungjukVO2 vo2) {
		int res = 0;
		
		try {
			sql = "insert into sungjuk values (default,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage() );
		} finally {
			pstmtClose();
		}
		
		return res;
	}
	
	//성적 수정
	public int setSungjukUpdate(SungjukVO2 vo2) {
		int res = 0;
		
		try {
			sql = "update sungjuk set name=?, kor=?, eng=?, mat=? where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
					
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " +  e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	//성적 삭제
	public int setSungjukDelete(int idx) {
		int res = 0;
		
		try {
			sql = "delece from sungjuk where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " +  e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}
	
	
	
}
