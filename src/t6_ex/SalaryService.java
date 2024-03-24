/*
package t6_ex;


import java.util.ArrayList;
import java.util.Scanner;

public class SalaryService {
	
	Scanner sc = new Scanner(System.in);
	NetpayDAO dao = new NetpayDAO();
	InsaVO vo = null;
	
	String ans = "N";
	int choice = 0, res = 0;
	
	//직급/본봉 조회
	public void setSalaryList() {
		ArrayList<InsaVO> vos = dao.getSalaryList();
		
		System.out.println("\n\t***본봉 테이블***");
		System.out.println("=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=.=");
		
	}
	
	//직급/본봉 입력
	public void setSalaryInput() {
		System.out.println("\n **본봉 테이블 입력**");
		String jikkub = "";
		
		while(true) {
			System.out.println("직급 : ");
			jikkub = sc.next();
			vo = dao.getJikkubSearch(jikkub);
			if(vo == null) break; //동명이인 처리?
			else System.out.println("같은 직급이 존재합니다. 다시 입력해 주세요.");
			
		}
		vo = new InsaVO();
		vo.setJikkub(jikkub);
		System.out.println("본봉 : ");
		vo.setBonbong(sc.nextInt());
		
		int res = dao.setSalaryInput(vo);
		
		if(res != 0) System.out.println("본봉 테이블 입력이 완료되었습니다.");
		else System.out.println("!!!본봉 테이블 입력에 실패하였습니다. 다시 확인해주세요.!!!");
	}

	//직급/본봉 수정
	public void setSalaryUpdate() {
		// TODO Auto-generated method stub
		
	}

	//직급/본봉 삭제
	public void setSalaryDelete() {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
*/
