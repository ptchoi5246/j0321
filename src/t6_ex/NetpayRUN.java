/*
package t6_ex;

import java.util.Scanner;

public class NetpayRUN {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SalaryService salaryService = new SalaryService();
		InsaService insaService = new InsaService();
		NetpayService NetpayService = new NetpayService();
		
		int choice = 0;
		boolean run = true;
		
		while(run) {
			System.out.println("\n\t **그 린 컴 퍼 니 인 사/ 급 여 관 리");
			System.out.println("원하시는 메뉴를 입력하세요~!  ");
			System.out.print("메뉴 선택 : 1.본봉테이블 관리  2.인사 관리  3.급여 관리  0.종료 ==>  ");
			choice = sc.nextInt();
			
			switch(choice) {
				case 1: //본봉 테이블 관리 - 1.직급/본봉 조회  2.직급/본봉 입력  3.직급/본봉 수정  4.직급/본봉 삭제  0.종료
					while(true) {
						System.out.println("\n\t **그 린 컴 퍼 니 본봉 테이블 관리**");
						System.out.print("메뉴 선택 : 1.직급/본봉 조회  2.직급/본봉 입력  3.직급/본봉 수정  4.직급/본봉 삭제  0.종료 ==>  ");
						choice = sc.nextInt();
						
						if(choice == 1) salaryService.setSalaryList(); //직급, 본봉 조회
						else if (choice == 2) salaryService.setSalaryInput(); //직급, 본봉 입력
						else if (choice == 3) salaryService.setSalaryUpdate(); //직급, 본봉 수정
						else if (choice == 4) salaryService.setSalaryDelete(); //직급, 본봉 수정
						else break;
					}
					
				case 2: //인사 관리
					System.out.println("\n\t **그 린 컴 퍼 니 인사 관리**");
					System.out.print("메뉴 선택 : 1.사원 전체 조회  2.사원 등록  3.사원 개별 조회  0.종료 ==>  ");
					choice = sc.nextInt();
					
					break;
					
				case 3: //급여 관리
					
					break;
					
				
				default: //종료
					run = false;
						
			}
		}
		
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		System.out.println("작업이 끝났습니다. 감사합니다.");
				
		sc.close();
	}
}
*/
