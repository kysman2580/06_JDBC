package com.kh.mvc2.model.service;

import java.sql.Connection;
import java.util.List;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.util.JdbcUtil;
import com.kh.mvc2.model.dao.UserDAO2;
import com.kh.mvc2.model.dto.UserDTO2;
import com.kh.mvc2.util.JdbcUtil2;

/**
 * Service : 비즈니스로직 / 의사결정코드를 작성하는 부분
 * Controller에서는 Service단의 메소드를 호출
 * Service에서 실질적으로 동작시켜야 하는 코드를 작성
 * -> Service단을 추가함으로써 DAO는 순수하게 SQL문을
 * 	  처리하는 부분만 남겨놓을 것
 */
public class MemberService2 {
	private UserDAO2 userDao2 = new UserDAO2();
	
	public List<UserDTO2> findAll(){
		
		Connection conn = JdbcUtil2.getConnection();
		
		List<UserDTO2> list = userDao2.findAll(conn);
		
		return list;
	}
	

	
}
