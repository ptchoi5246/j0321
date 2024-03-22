package t5_CRUD;

import java.util.Scanner;

public class SungjukRun {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SungjukService service = new SungjukService();		
		
		int choice = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("\n\t ** 성  적  표 **");
			System.out.print("메뉴 선택 : 1.입력 2.전체조회 3.개별조회 4.수정 5.삭제 0.종료 ==>");
			choice = sc.nextInt();
			
			switch (choice) {
				case 1: //입력
					service.setSungjukInput();
					break;
				case 2: //전체조회
					service.getSungjukList();
					break;
				case 3: //개별조회
					service.getSungjukSearch();
					break;
				case 4: //수정
					service.setSungjukUpdate();
					break;
				case 5: //삭제
					service.setSungjukDelete();
					break;

				default:
					run = false;
			}
		}
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("작업이 끝났습니다. :)");
		
		sc.close();
	}
}
