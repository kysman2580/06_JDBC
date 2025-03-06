package com.kh.mvc.view;
// View에서는 최대한 코드 작성 X

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;

/**
 * MemberView 클래스는 JDBC 실습을 위해 생성하였으며, 
 * 
 * @date : 2025-03-04
 */
public class UserView {

	private Scanner sc = new Scanner(System.in);

	// composition(합성)을 하려면 private로 선언해준다.
	private UserController userController = new UserController();

	/**
	 * 프로그램 시작 시 사용자에게 보여줄 메인화면을 출력해주는 메소드입니다.
	 */
	public void mainMenu() {

		while (true) {
			System.out.println("--- USER테이블 관리 프로그램 ---");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 회원 추가");
			System.out.println("3. 비밀번호 변경");
			System.out.println("4. 회원 삭제");
			System.out.println("5. 회원 번호로 단일 회원 조회");

			System.out.println("6. 회원 아이디 검색 조회");
			System.out.println("9. 프로그램 종료");
			System.out.print("이용할 메뉴를 선택해주세요 > ");

			int menuNo = 0;

			try {

				menuNo = sc.nextInt();
				sc.nextLine();

			} catch (InputMismatchException e) {
				continue;
			}

			switch (menuNo) {
			case 1:
				findAll();
				break;
			case 2:
				insertUser();
				break;
			case 3:
				updatePW();
				break;
			case 4:
				deleteUser();
				break;
			case 5:
				noSelect();
				break;
			case 6:
				idSerach();
			case 9:
				System.out.println("프로그램 종료~");
				return;
			default:
				System.out.println("잘못된 메뉴 선택입니다");
			}

			// 1) 회원 조회를 하기 위한 첫 번째 절차
			// 1. DB 서버에 접근.

		}
	}

	// 회원 전체 정보를 조회해주는 기능
	private void findAll() {

		System.out.println("\n--- 회원 전체 목록입니당 ---");
		// Controller한테 DB 회원 전체 목록 가져오게 지시
		List<UserDTO> list = userController.findAll();

		System.out.println("\n조회된 총 회원의 수는 " + list.size() + "명 입니다.");

		if (!list.isEmpty()) {
			System.out.println("=====================================");
			for (UserDTO user : list) {
				System.out.println("\n님의 정보 : " + user.getUserName());
				System.out.println("님의 아이디 : " + user.getUserId());
				System.out.println("님의 비밀번호 : " + user.getUserPw());
				System.out.println("님의 번호 : " + user.getUserNo());
				System.out.println();
			}
			System.out.println("=====================================");
		} else {
			System.out.println("회원이 존재하지 않습니다.");
		}

	}

	/**
	 * TB_USER에 INSERT할 값을 사용자에게 입력받도록 유도하는 화면
	 */
	private void insertUser() {

		System.out.println("\n--- 회원 추가 페이지입니다. ---");

		System.out.print("아이디를 입력하세요 > ");
		String userId = sc.nextLine();

		System.out.print("비밀번호를 입력하세요 > ");
		String userPw = sc.nextLine();

		System.out.print("이름을 입력하세요 > ");
		String userName = sc.nextLine();

		int result = userController.insertUser(userId, userPw, userName);

		if (result > 0) {
			System.out.println(userName + "님 가입에 성공하셨습니다.");
		} else {
			System.out.println("회원 추가에 실패했습니다. 다시 시도해주세요.");
		}
	}

	/**
	 * TB_USER에 USER_PW 수정하기
	 */
	private void updatePW() {
		List<UserDTO> list = userController.findAll();

		System.out.println("--- 비밀번호 수정 페이지 입니다 ---");

		System.out.print("비밀번호를 수정할 아이디를 입력하세요 > ");
		String userId = sc.nextLine();

		boolean userFound = false;
		for (int i = 0; i < list.size(); i++) {
			// 입력한 아이디가 리스트에 있으면 수행

			if (userId.equals(list.get(i).getUserId())) {
				userFound = true;

				System.out.print("기존 비밀번호를 입력하세요 > ");
				String userPw = sc.nextLine();

				if (userPw.equals(list.get(i).getUserPw())) {
					// 입력한 아이디의 비밀번호가 기존 비밀번호와 일치하면 수행
					System.out.print("새롭게 변경할 비밀번호를 입력하세요 >");
					String userNpw = sc.nextLine();

					int result = userController.updatePw(userId, userNpw);

					if (result > 0) {
						System.out.println("비밀번호가 성공적으로 번경되었습니다.");
					} else {
						System.out.println("비밀번호 변경에 실패했습니다.");
					}
				} else {
					System.out.println("기존 비밀번호와 일치하지 않습니다.");
				}
				break;
			}

		}
		if (!userFound) {
			System.out.println("해당 아이디를 찾을 수 없습니다.");
		}
	}
	

	private void deleteUser() {
		List<UserDTO> list = userController.findAll();

		System.out.println("\n --- 회원 삭제 페이지입니다 ---");

		System.out.print("삭제할 회원 번호를 입력해주세요 > ");
		int userNo = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserNo() == userNo) {

				System.out.print("정말 삭제하시겠습니까?(Y & N) > ");
				String yn = sc.nextLine().toUpperCase();

				do {
					if (yn.equals("Y")) {
						userController.deleteUser(userNo);
						System.out.printf("%d번 회원이 삭제되었습니다.\n", userNo);
						return;

					} else if (yn.equals("N")) {
						System.out.println("삭제가 취소되었습니다.");
						return;
					} else {
						System.out.println("Y 또는 N을 입력해주세요");
						break;
					}

				} while (yn != null);

			} else {
				System.out.println("일치하는 회원 번호가 없습니다.");
				break;
			}
		}
	}

	// 회원 번호로 단일 회원 조회
	private void noSelect() {
		List<UserDTO> list = userController.findAll();

		System.out.println("\n --- 단일 회원 조회 페이지입니다 ---");

		System.out.print("조회할 회원 번호를 입력해주세요 > ");
		int userNo = sc.nextInt();
		sc.nextLine();

		boolean found = false;

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserNo() == userNo) {
				found = true;
				break;
			}
		}
		if(found) {
			List<UserDTO> result = userController.noSelect(userNo);
			if(result != null && !result.isEmpty()) {
				System.out.printf("%d번째 회원을 조회했습니다.\n", userNo);
				
				UserDTO user = result.get(0);
				System.out.println("번호 : " + user.getUserNo());
				System.out.println("아이디 : " + user.getUserId());
				System.out.println("비밀번호 : " + user.getUserPw());
				System.out.println("이름 : " + user.getUserName());
				System.out.println("가입일 : " + user.getEnrollDate());
			}else {
	            System.out.println("회원 정보를 불러오는 데 실패했습니다.");
			}
		
		}else {
			System.out.println("일치하는 회원 번호가 없습니다.");
		}

	}
	
	
	public void idSerach() {
		List<UserDTO> list = userController.findAll();
		
		System.out.println("\n --- 아이디 검색 조회 페이지입니다 ---");

		System.out.print("검색할 아이디를 입력해주세요 > ");
		String userId = sc.nextLine();

		boolean found = false;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getUserId().equals(userId)) {
				found = true;
				break;
			}
		}
		if(found) {
			List<UserDTO> result = userController.idSerach(userId);
			if(result != null && !result.isEmpty()) {
				UserDTO user = result.get(0);
				System.out.println("아이디 : " + user.getUserId());
				System.out.println("비밀번호 : " + user.getUserPw());
				System.out.println("이름 : " + user.getUserName());
				System.out.println("가입일 : " + user.getEnrollDate());
			}else {
				System.out.println("아이디 조회에 실패했습니다.");
			}
		}else {
			System.out.println("일치하는 아이디가 없습니다.");
		}
		
		
		
	}
	
	
	
	
	
	
	
}
