package t4_CRUD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class HoewonDAO2 { //DAO : Data Access Object :: 데이터베이스 관리
	
	//필수 객체 -- 미리 선언하기
	private Connection conn = null; //mysql서버 접속해서 데이터베이스 연결하기까지가 conn의 역할
	//DAO 객체생성 후 연결 끝 - connClose conn.close
	private Statement stmt  = null; //conn를 통해서 불러오기 //sql명령을 쓰려면 Statement 무조건 있어야한다.
	private ResultSet rs = null; //결과값을 받을 객체 select - 무조건!!!
	
	HoewonVO vo = null;
	//sql 명령어
	private String sql = "";
	
	public HoewonDAO2() {
		try {
			Class.forName("com.mysql.jdbc.Driver"); //외우기
			
			String url = "jdbc:mysql://localhost:3306/javaclass"; //외우기
			//String url = "jdbc:mysql://127.0.0.1:3306/javaclass"; // ip주소 랜카드 주소
			//String url = "jdbc:mysql://192.168.50.61:3306/javaclass"; // 학원 사설 ip주소
			String user = "atom";
			String pwd = "1234";
			conn = DriverManager.getConnection(url, user, pwd);
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 검색 실패"); 
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		}
	}
	
	public void connClose() { //메소드 새로 만들기
		try {
			conn.close();
		} catch (SQLException e) {}
	}
	
	public void stmtClose() {
		try {
			if(stmt != null) stmt.close(); //stmt 안 열었는데 닫으면 오류, stmt를 사용하지 않으면 닫아라
		} catch (SQLException e) {}
	}
	
	public void rsClose(){
		try {
			if(rs != null) rs.close();
			stmt.close(); //rs 닫을 때는 stmt도 무조건 닫아야 하기에 stmt 호출
		} catch (SQLException e) {}
	}

	//Hoewon 전체 조회
	public ArrayList<HoewonVO> getList() {
		ArrayList<HoewonVO> vos = new ArrayList<HoewonVO>(); //리턴타입 생성
		try {
			stmt  = conn.createStatement();
			sql = "select * from hoewon"; //sql 명령
			rs = stmt.executeQuery(sql); //select - 결과값을 돌려 받는다 :: excuteQuery
			
			//HoewonVO vo = null; //필드에서 선언
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
		} finally {
			rsClose(); //rs, stmt 같이 닫기
		} //table 제어 준비 끝
		return vos; //리턴값
	}
	
	
	//개별 조회
	public HoewonVO getSearch(String name) {
		HoewonVO vo = new HoewonVO(); //리턴타입으로 넘겨야하니까 미리 생성하는게 낫다
		
		try {
			stmt  = conn.createStatement();
			sql = "select * from hoewon where name = '"+name+"'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				vo.setIdx(rs.getInt("idx"));
				vo.setName(rs.getString("name"));
				vo.setAge(rs.getInt("age"));
				vo.setGender(rs.getNString("gender"));
				vo.setAddress(rs.getString("address"));
			}
			
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			rsClose(); //rs, stmt 같이 닫기
		} //table 제어 준비 끝
		
		return vo; //리턴값
	}
	
	/* t3_CRUD 자료 수정처리 Service 
	// 회원자료 수정처리 
	// System.out.print("수정할 항목? 1.성명  2.나이  3.성별  4.주소 ==> ");
	public void setUpdate(int idx, int choice, String content) {
		try {
			stmt = conn.createStatement();
			
			if(choice == 1) {
				sql = "update hoewon set name='"+content+"' where idx=" + idx;
			}
			else if(choice == 2) {
				sql = "update hoewon set age="+Integer.parseInt(content)+" where idx=" + idx;
			}
			else if(choice == 3) {
				sql = "update hoewon set gender='"+content+"' where idx=" + idx;
			}
			else if(choice == 4) {
				sql = "update hoewon set address='"+content+"' where idx=" + idx;
			}
			stmt.executeUpdate(sql);//select 빼고 나머지는 executeUpdate
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose(); //stmt 닫기
		}
	}
*/
	
	//회원 삭제 처리
	public void setDelete(String name) {
		try {
			stmt  = conn.createStatement();
			sql = "delete from hoewon where name = '"+name+"'";
			stmt.executeUpdate(sql); //select 빼고 나머지는 executeUpdate
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose(); //stmt 닫기
		}
	}
	
	//회원 등록 처리
	public void setInput(HoewonVO vo) {
		try {
			stmt  = conn.createStatement();
			sql = "insert into hoewon values (default, '"+vo.getName()+"', "+vo.getAge()+", '"+vo.getGender()+"', '"+vo.getAddress()+"' )";
			stmt.executeUpdate(sql); //select 빼고 나머지는 executeUpdate
		} catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose(); //stmt 닫기
		}
	}

	
	//회원자료 수정처리 수정
	public int setUpdate(HoewonVO vo) {
		//System.out.println("vo : " + vo);
		int res = 0;
		try {
			stmt = conn.createStatement();
			sql = "update hoewon set name= '"+vo.getName()+"', age= "+vo.getAge()+", gender= '"+vo.getGender()+"', address= '"+vo.getAddress()+"' where idx=" + vo.getIdx();
			res = stmt.executeUpdate(sql);//select 빼고 나머지는 executeUpdate
			//System.out.println("res : " + res);
		}catch (SQLException e) {
			System.out.println("SQL 오류 : " + e.getMessage());
		} finally {
			stmtClose(); //stmt 닫기
		}
		return res;
	
	}
}	
