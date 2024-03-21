package t3_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class HoewonService {
	Scanner sc = new Scanner(System.in);

	
	//전체 조회
	public void getList() {
		//HoewonDAO dao = new HoewonDAO();
		HoewonDAO2 dao = new HoewonDAO2();
		
		ArrayList<HoewonVO> vos = dao.getList(); //database에 가서 자료를 가져오세요 return 타입 있어야 한다.
		HoewonVO vo = new HoewonVO();
		
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("번호\t성명\t나이\t성별\t주소");
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		for(int i=0; i<vos.size(); i++) { //vos.size : 크기 아니까 for문 사용 //출력할 때는 vo 하나만 있으면 된다. 가져와서 쓰고 돌려주고, 가져와서 쓰고 돌려주고 : 재사용 가능
			vo = vos.get(i);
			System.out.print(vo.getIdx() + "\t"); //테이블에 있는 필드
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getAge() + "\t");
			System.out.print(vo.getGender() + "\t");
			System.out.print(vo.getAddress() + "\n");
		}
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("\t\t총  : "+vos.size()+" 건");
		
		dao.connClose(); //작업이 끝나면 close
	}
	
	//개별 조회
	public void getSearch(String name) {
		HoewonDAO2 dao = new HoewonDAO2();
		
		HoewonVO vo =	dao.getSearch(name);
		
		System.out.println(name + " 으로 검색된 자료 :: ");
		if(vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());
			System.out.println("이름 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
		}
		else System.out.println("검색한 자료가 없습니다.");
		
		dao.connClose();
	}
	
	
	//회원 정보 수정 -- 검색 먼저
	public void setUpdate(String name) {
		
		HoewonDAO2 dao = new HoewonDAO2();
		
		HoewonVO vo =	dao.getSearch(name); //자료 검색
		
		System.out.println(name + " 으로 검색된 자료 :: "); //검색한 자료를 먼저 보여주고 무엇을 수정할건지 물어본다.
		if(vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());
			System.out.println("이름 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
			System.out.println("===================================");
			System.out.print("수정할 항목을 입력하세요~!  1.성명	2.나이	3.성별	4.주소 ==>  ");
			int choice = sc.nextInt();
			System.out.println("수정할 내용을 입력하세요~!  ==>");
			String content = sc.next();
			
			dao.setUpdate(vo.getIdx(), choice, content); //넘겨줄 수정할 것 : choice, content 그리고!!!!! idx!!!!!
			//retrun 무조건 숫자로 반환! 0 : 수정되지 않았다. !0 : 수정되었다.
			System.out.println("==>  자료가 수정되었습니다.");
			
		}
		else System.out.println("검색한 자료가 없습니다.");
		
		dao.connClose();
	}

	
	//회원자료 삭제처리
	public void setDelete(String name) {
HoewonDAO2 dao = new HoewonDAO2();
		
		HoewonVO vo =	dao.getSearch(name); //자료 검색
		
		System.out.println(name + " 으로 검색된 자료 :: "); //검색한 자료를 먼저 보여주고 무엇을 수정할건지 물어본다.
		if(vo.getName() != null) {
			System.out.println("번호 : " + vo.getIdx());
			System.out.println("이름 : " + vo.getName());
			System.out.println("나이 : " + vo.getAge());
			System.out.println("성별 : " + vo.getGender());
			System.out.println("주소 : " + vo.getAddress());
			System.out.println("===================================");
			System.out.print("삭제하시겠습니까? (y/n) ==?	");
			String choice = sc.next();
			if(choice.toUpperCase().equals("Y")) {
				dao.setDelete(name);
				System.out.println("==> 삭제 완료");
				
			}
			else {
				System.out.println("==> 삭제 취소");
			}
			
			
		}
		else System.out.println("검색한 자료가 없습니다.");
		
		dao.connClose();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
