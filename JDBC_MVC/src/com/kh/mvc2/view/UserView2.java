package com.kh.mvc2.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.kh.mvc.controller.UserController;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc2.controller.UserController2;
import com.kh.mvc2.model.dto.UserDTO2;

/**
 * 사용자에게 입력 및 출력을 수행하는 메소드를 제공
 */
public class UserView2 {
	private Scanner sc = new Scanner(System.in);

	UserController2 userController2 = new UserController2();

	public void menu() {

		while (true) {
			System.out.println("--- EMPLOYEE 	테이블 관리 프로그램 ---");
			System.out.println("1. 회원 전체 조회");
			System.out.println("2. 이름 변경 업데이트");
			System.out.println("3. 회원 추가");
			System.out.println("4. 회원 삭제");
			
			
			
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
				updateName();
				break;
			case 3:
				insertUser();
				break;
			case 4:
				deleteUser();
				break;
			case 9:
				System.out.println("프로그램 종료~");
				return;
			default:
				System.out.println("잘못된 메뉴 선택입니다");
			}

		}
	}

// 전체 조회
private void findAll() {
	System.out.println("\n --- 전체 조회 메서드입니다 ---");
	
	List<UserDTO2> list = userController2.findAll();
			
	System.out.printf("총 조회된 회원의 수는 %d명 입니다. ", list.size());
	
	if(!list.isEmpty()) {
		System.out.println("===============================================");
		for(UserDTO2 user : list) {
			System.out.println(user.getEmpId() + "|" + 
							   user.getEmpName() + "|" + 
							   user.getEmpNo() + 
							   user.getEmail() + "|" + 
							   user.getPhone());						
		}
		System.out.println("===============================================");
	}else {
		System.out.println("회원이 비어있습니다.");
	}
}



public void insertUser() {
    System.out.println("\n--- 회원 추가 메서드입니다 ---");
    System.out.print("추가할 회원의 이름을 입력하세요 > ");
    String name = sc.nextLine();
    System.out.print("회원의 이메일을 입력하세요 > ");
    String email = sc.nextLine();
    System.out.print("회원의 전화번호를 입력하세요 > ");
    String phone = sc.nextLine();

    int result = userController2.insertUser(name, email, phone);

    if (result > 0) {
        System.out.println("회원이 성공적으로 추가되었습니다.");
    } else {
        System.out.println("회원 추가에 실패했습니다.");
    }
}


private void updateName() {
	System.out.println("\n --- 회원 이름 수정 메서드입니다 ---");
	
	System.out.print("기존 이름을 입력하세요 > ");
	String userName = sc.nextLine();
	
	
	System.out.print("새로운 이름을 입력하세요 > ");
	String newName = sc.nextLine();
	
	int result = userController2.updateName(userName, newName);
	
	if (result > 0) {
        System.out.println("이름이 성공적으로 변경되었습니다.");
    } else {
        System.out.println("이름 변경에 실패했습니다. 기존 이름을 확인하세요.");
    }
	
}


public void deleteUser() {
    System.out.println("\n--- 회원 삭제 메서드입니다 ---");
    System.out.print("삭제할 회원의 이름을 입력하세요 > ");
    String name = sc.nextLine();

    int result = userController2.deleteUser(name);

    if (result > 0) {
        System.out.println("회원이 성공적으로 삭제되었습니다.");
    } else {
        System.out.println("회원 삭제에 실패했습니다. 이름을 확인하세요.");
    }
}

	
	
	
	
	
	
	
	
	
	
	
}
