package t5_CRUD_2;

import java.util.ArrayList;
import java.util.Scanner;

public class SungjukService2 {
	Scanner sc = new Scanner(System.in);
	SungjukDAO2 dao = new SungjukDAO2();
	SungjukVO2 vo = null;
	
	int res, choice =0;
	String ans = "N";
	
	
	//전체 조회
	public void getSungjukList() {
		ArrayList<SungjukVO2> vos = dao.getSungjukList();
		
		System.out.println("\n\t ** 성 적 리 스 트 **");
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("번소\t성명\t국어\t영어\t수학\t총점\t평균\t학점");
		System.out.println("-----------------------------------------");
		for(int i=0; i<vos.size(); i++) {
			vo = vos.get(i);
			calculator (vo);
			System.out.println(" " + (i+1) + "\t");
			System.out.println(vo.getName() + "\t");
			System.out.println(vo.getKor() + "\t");
			System.out.println(vo.getEng() + "\t");
			System.out.println(vo.getMat() + "\t");
			System.out.println(vo.getTot() + "\t");
			System.out.println(String.format("%.1f", vo.getAvg()) + "\t");
			System.out.println(vo.getGrade() + "\n");
		}
		System.out.println("-----------------------------------------");
		System.out.println("\t 총 인원수 : " + vos.size() + "명");
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
	}

	//전체 조회 - 계산(총점,평균,학점 구하기
	private void calculator(SungjukVO2 vo2) {
		vo.setTot(vo.getKor() + vo.getEng() + vo.getMat());
		vo.setAvg(vo.getTot() / 3.0);
		if(vo.getAvg() >= 90) vo.setGrade('A');
		else if(vo.getAvg() >= 80) vo.setGrade('B');
		else if(vo.getAvg() >= 70) vo.setGrade('C');
		else if(vo.getAvg() >= 60) vo.setGrade('D');
		else vo.setGrade('F');
	}

	//개별 성적 조회
	public void getSungjukSearch() {
		while(true) {
			sungjukBasicSearch();
			
			System.out.println("계속 하시겠습니까? (y/n) ==>  ");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")); break;
		}
	}
	
	//성적 조회, 성적 수정, 성적 삭제 - 이름 조회 // 공통 메소드
	public void sungjukBasicSearch() {
		
		System.out.println("\n 조회하실 성명을 입력하세요~!  ");
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
		else System.out.println("검색하신 "+name+" 님은 없습니다. 다시 확인해 주세요.");
	}
		
	
	//성적 입력
	public void setSungjukInput() {
		
		while(true) {
			System.out.println("\n ** 성적 입력 처리 **");
			String name = "";
			int kor=0, eng=0, mat=0;
			
			while(true) {
				System.out.println("성명 : ");
				name = sc.next();
				
				vo = dao.getSungjukSearch(name);
				if(vo==null) break;
				else System.out.println("같은 이름이 존재합니다. 다시 확인해 주세요");
			}
			System.out.println("국어 : ");
			kor = sc.nextInt();
			System.out.println("영어 : ");
			eng = sc.nextInt();
			System.out.println("수학 : ");
			mat = sc.nextInt();
			
			SungjukVO2 vo = new SungjukVO2();
			vo.setName(name);
			vo.setKor(kor);
			vo.setEng(eng);
			vo.setMat(mat);
			
			int res = dao.setSungjukInput(vo);
			 
			if(res != 0) System.out.println("** 성적 입력 완료 **");
			else System.out.println("!!! 성적 입력 실패 !!!");
			
			System.out.println("계속 하시겠습니까? y/n  ==>  ");
			ans = sc.next();
			if(!ans.toUpperCase().equals("Y")) break;
		}
	}
	
	//성적 수정
	public void setSungjukUpdate() {
		sungjukBasicSearch();
		boolean run = true;
		
		while(run) {
			System.out.println("수정 항목 선택 : 1.성명  2.국어  3.영어  4.수학  0.종료");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: vo.setName(sc.next()); break;
				case 2: vo.setKor(sc.nextInt()); break;
				case 3: vo.setEng(sc.nextInt()); break;
				case 4: vo.setMat(sc.nextInt()); break;
				default : run = false;
			}
		}
		
		res = dao.setSungjukUpdate(vo);
		
		if(res != 0) System.out.println("성적 내용이 수정되었습니다.");
		else System.out.println("수정된 내용이 없습니다.");
	}

	//성적 삭제
	public void setSungjukDelete() {
		sungjukBasicSearch();
		
		if(vo != null) {
			System.out.println("삭제하시겠습니까?  y/n  =>  ");
			ans = sc.next();
			if(ans.toUpperCase().equals("Y")) {
				res = dao.setSungjukDelete(vo.getIdx());
				if(res != 0) System.out.println(vo.getName() + "님의 성적 자료가 삭제되었습니다.");
				else System.out.println(vo.getName() + "님의 성적 자료가 없습니다.");
				
			}
			else System.out.println("!!! 성적 삭제 실패 !!!");
		}
	}
	
	
	
	

}
