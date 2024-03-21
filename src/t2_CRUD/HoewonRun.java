package t2_CRUD;

public class HoewonRun {
	public static void main(String[] args) {
		
		//HoewonDAO dao = new HoewonDAO();
		//HoewonDAO2 dao = new HoewonDAO2(); //rs.next() while문
		HoewonDAO3 dao = new HoewonDAO3();
		
//		HoewonService service = new HoewonService(); //일반적인 작업, CRUD에는 안 만들어도 됨
		
		//CRUD 의 Select :: hoewon 자료 전체 조회
		dao.getList();
		
		
		
		dao.connClose();
	}
}
