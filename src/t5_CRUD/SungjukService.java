package t5_CRUD;

import java.util.ArrayList;
import java.util.Scanner;

public class SungjukService {
	
	Scanner sc = new Scanner(System.in); //sc.close 하지 않기 :: 반복해서 다시 들어올 때 닫혀있음
	SungjukDAO dao = new SungjukDAO(); //닫지 않기
	SungjukVO vo= null;
	
	int res, choice = 0;
	String ans="N";
	
	// 성적 입력 - scanner 사용, database 사용 
	public void setSungjukInput() {
		
		while(true) {
			System.out.println("\n ** 성적 입력 처리 **");
			//변수 선언하기
			String name = "";
			int kor=0, eng=0, mat=0;
			
			while(true) {
				System.out.print("성명 : "); name = sc.next();
				//동명이인 처리
				vo = dao.getSungjukSearch(name); //동명이인 처리 - 개별 검색과 같다
				if(vo == null) break; //값이 없으면(동명이인이 없으면) 가입 가능 while문 나가기 break;
				else System.out.println("같은 이름이 존재합니다. 다른 이름을 입력해주세요.");
			}
			System.out.print("국어 : "); kor = sc.nextInt();
			System.out.print("영어 : "); eng = sc.nextInt();
			System.out.print("수학 : "); mat = sc.nextInt();
			
			SungjukVO vo = new SungjukVO();
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMat(mat);
			
			int res = dao.setSungjukInput(vo);
			
			if(res != 0) System.out.println("** 성적 입력 완료 **");
			else System.out.println("!!!성적 입력 실패!!!");
			
			System.out.print("계속 하시겠습니까? (y/n)  ==>");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")); break;
		}
		//sc.close(); //닫지 않기, 닫으니까 반복할 때마다 오류
		
	}
	// 회원 전체 검색
	public void getSungjukList() {
		ArrayList<SungjukVO> vos = dao.getSungjukList();
		
		System.out.println("\n\t ** 성 적 리 스 트 **");
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("번호\t성명\t국어\t영어\t수학\t총점\t평균\t학점");
		System.out.println("-----------------------------------------------------");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i); //vos에서 꺼낸게 vo
			calculator (vo); //vo에 담겨진 점수로 총점 구하기
			System.out.print(" " + (i+1) + "\t");
			System.out.print(vo.getName() + "\t");
			System.out.print(vo.getKor() + "\t");
			System.out.print(vo.getEng() + "\t");
			System.out.print(vo.getMat() + "\t");
			System.out.print(vo.getTot() + "\t");
			System.out.print(String.format("%.1f", vo.getAvg()) + "\t");
			System.out.print(vo.getGrade() + "\n");
		}
		System.out.println("-----------------------------------------------------");
		System.out.println("\t 총 인원수 : " + vos.size() + "명");
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
	}
	
	//성적리스트 - 계산(총점,평균,학점 구하기)
	private void calculator(SungjukVO vo) {
		vo.setTot(vo.getKor()+ vo.getEng()+vo.getMat());
		vo.setAvg(vo.getTot() / 3.0); //avg는 double
		if(vo.getAvg() >= 90) vo.setGrade('A');
		else if(vo.getAvg() >= 80) vo.setGrade('B');
		else if(vo.getAvg() >= 70) vo.setGrade('C');
		else if(vo.getAvg() >= 60) vo.setGrade('D');
		else vo.setGrade('D');
		//return 하지 않아도 calculator (vo)에 저장이 잘 된다. // vo 새로 생성하지 않아서 같다.
	}
	
	//개별 자료 검색
	public void getSungjukSearch() {
		while(true) {
			sungjukBasicSearch();
			
			System.out.print("계속 하시겠습니까? (y/n)  ==>");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")); break;
			}
	}
	
	//회원 정보 수정하기
	public void setSungjukUpdate() {
		sungjukBasicSearch();
		//System.out.println("vo : " + vo); vo 값이 아직 남아있는지 출력시켜보기
		boolean run = true;
		
		while(run) {
			System.out.print("수정 항목 선택 : 1.성명 2.국어 3.영어 4.수학 0.종료 ==>  ");
			choice = sc.nextInt();
			System.out.print("수정할 내용을 입력하세요~!  ");
			
			switch (choice) {
				case 1: vo.setName(sc.next()); break;
				case 2: vo.setKor(sc.nextInt()); break;
				case 3: vo.setEng(sc.nextInt()); break;
				case 4: vo.setMat(sc.nextInt()); break;
				default: run = false;
			}
		}
		res = dao.setSungjukUpdate(vo);
		
		if(res != 0) System.out.println("성적 내역이 수정되었습니다.");
		else System.out.println("수정된 내용이 없습니다.");
	}
	
	private void sungjukBasicSearch() {
		System.out.print("\n조회할 성명을 입력하세요  ");
		String name = sc.next();
		
		vo = dao.getSungjukSearch(name);
		
		if(vo != null) {
			calculator(vo);
			System.out.println("\n고유번호 : " + vo.getIdx());
			System.out.println("성명 : " + vo.getName());
			System.out.println("국어 : " + vo.getKor());
			System.out.println("영어 : " + vo.getEng());
			System.out.println("수학 : " + vo.getMat());
			System.out.println("총점 : " + vo.getTot());
			System.out.println("평균 : " + vo.getAvg());
			System.out.println("학점 : " + vo.getGrade());
		}
		else System.out.println("검색하신 "+name+" 님은 없습니다. 다시 확인해주세요.");
	}

	//점수 삭제하기
	public void setSungjukDelete() {
		sungjukBasicSearch();
		
		if(vo != null) {
			System.out.println("삭제 하시겠습니까? (y/n) ==>");
			ans = sc.next();
			if(ans.toUpperCase().equals("Y")){
				res = dao.setSungjukDelete(vo.getIdx()); //동명이인 때문에 idx 회원번호: 날짜
				if(res != 0) System.out.println(vo.getName() + " 님의 성적 자료가 삭제되었습니다."); //삭제 - res 1
				else System.out.println(vo.getName() + " 님의 자료가 없습니다.");
			} else System.out.println("!!!삭제 실패!!!");
		}
	}
	
}
