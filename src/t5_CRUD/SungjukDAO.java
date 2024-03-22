package t5_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SungjukDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	SungjukVO vo = null;
	String sql = "";
	
	public SungjukDAO() {
		
		String url = "jdbc:mysql://localhost:3306/javaclass";
		String user = "atom";
		String pwd = "1234";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, user, pwd);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패 - 드라이버 이름 확인" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("DB 연결 실패" + e.getMessage());
		}
	}
	
	// conn객체 Close
	public void connClose() {
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	// pstmt 객체 Close
	public void pstmtClose() {
		try {
			if(pstmt != null)pstmt.close();
		}
		catch (SQLException e) {}
	}
	
	//rs객체 Close
	public void rsClose() {
		try {
			if(rs != null) rs.close();
			pstmtClose();
		} catch (Exception e) {}
	}
	//여기까지가 기본작업 줄줄 쳐야된다.

	
	//Service에서 넘어오기
	//성적 자료 입력처리
	public int setSungjukInput(SungjukVO vo) {
		int res = 0;
		try { //prepared Statement : sql - 생성 - 실행
			sql = "insert into sungjuk values (default,?,?,?,?)"; //  '?'  작은 따옴표 생략, web으로 들어오는 입력은 모두 문자로 생각
			pstmt = conn.prepareStatement(sql); //prepareStatment 생성할 때 (sql)
			pstmt.setString(1, vo.getName()); //물음표 갯수에 맞게 순서대로 채워주기
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose(); //sql 명령이 set이라서 pstmt close - rs close
		}
	return res;
	}
	
	//성명조회(개별검색/동명이인 검색)처리
	public SungjukVO getSungjukSearch(String name) {
		SungjukVO vo = new SungjukVO();
		try {
			sql = "select * from sungjuk where name = ?"; //이해 부족
			pstmt = conn.prepareStatement(sql); //이해부족
			pstmt.setString(1, name); //이해부족
			rs = pstmt.executeQuery(); //rs : 자료 담아주는 바구니
			if(rs.next()) {//rs에 값이 있느냐? 있으면 true, 없으면 false
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setKor(rs.getInt("kor"));
				vo.setEng(rs.getInt("eng"));
				vo.setMat(rs.getInt("mat")); //vo에 자료를 담는다.
			}
			else vo = null; //vo에 자료가 있는지 없는지 확인하기 위해 작성
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose(); //sql 명령이 select라서 rsClose
		}
		
		return vo;
	}
	
	//전체 자료 검색
	public ArrayList<SungjukVO> getSungjukList() {
		ArrayList<SungjukVO> vos = new ArrayList<SungjukVO>();
		
		try {
			sql = "select * from sungjuk order by name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				vo = new SungjukVO(); //데이터베이스에 있는 rs 를 vo로 넣기 위해 새로운 객체생성
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

	
	//성적 자료 수정
	public int setSungjukUpdate(SungjukVO vo) {
		int res = 0;
		try {
			sql = "update sungjuk set name=?, kor=?, eng=?, mat=? where idx=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setInt(2, vo.getKor());
			pstmt.setInt(3, vo.getEng());
			pstmt.setInt(4, vo.getMat());
			pstmt.setInt(5, vo.getIdx());
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		
		return res;
	}
	
	//성적자료 삭제
	public int setSungjukDelete(int idx) { //Service 153 idx
		int res = 0;
		try {
			sql = "delete from sungjuk where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);
			res = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			pstmtClose();
		}
		return res;
	}

}	
