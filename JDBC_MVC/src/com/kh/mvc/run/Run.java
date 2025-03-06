package com.kh.mvc.run;

import com.kh.mvc.view.UserView;

public class Run {

	// JVM이 main이라는 이름의 메소드를 찾아서 실행시킴 (EntryPoint )
	public static void main(String[] args) {

		/*
		 * Model : 데이터 관련된 모든 작업 
		 * View : 화면 입 / 출력 
		 * Controller : Vew에서의 요청을 받아서 처리해주는 역할
		 */
		
		new UserView().mainMenu();
		
	}
}
