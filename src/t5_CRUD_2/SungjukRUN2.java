package t5_CRUD_2;

import java.util.Scanner;

public class SungjukRUN2 {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		SungjukService2 service2 = new SungjukService2();
		
		int choice = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("\n\t ** 성 적 표 **");
			System.out.print("메뉴 선택 : 1.전체조회  2.개별조회  3.성적 입력  4.성적 수정  5.성적 삭제  0.종료  ==>");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: //전체조회
					service2.getSungjukList();
					break;
					
				case 2: //개별조회
					service2.getSungjukSearch();
					break;
					
				case 3: //성적 입력
					service2.setSungjukInput();
					break;
					
				case 4: //성적 수정
					service2.setSungjukUpdate();
					break;
					
				case 5: //성적 삭제
					service2.setSungjukDelete();
					break;
					
				default :
					run = false;
					
			}
		}
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("작업이 끝났습니다. 감사합니다 :)");
		
		sc.close();
				
	}
}
