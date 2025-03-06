package com.kh.mvc2.controller;

import java.util.List;
import java.util.Map;

import com.kh.mvc2.model.dao.UserDAO2;
import com.kh.mvc2.model.dto.UserDTO2;
import com.kh.mvc2.model.service.MemberService2;

/**
 * View에서 온 요청을 받아 요청을 처리해주는 클래스.
 * 메소드로 전달된 데이터값을 가공처리한 후 DAO로 전달
 * DAO로부터 반환받은 결과를 사용자가 보게될 View(응답화면)에 반환.
 * 
 */
public class UserController2 {
	UserDAO2 userDao2 = new UserDAO2();
	MemberService2 service2 = new MemberService2();

	public List<UserDTO2> findAll() {
		return service2.findAll();
		
	}

	public int insertUser(String name, String email, String phone) {
        return userDao2.insertUser(name, email, phone);
	}
	
	
	public int updateName(String userName, String newName) {
		return userDao2.updateName(userName, newName);
	}
	
	public int deleteUser(String name) {
		
		
		return userDao2.deleteUser(name);
	}


}
