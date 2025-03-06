package com.kh.mvc.controller;

import java.util.List;
import java.util.Map;

import com.kh.mvc.model.dao.UserDAO;
import com.kh.mvc.model.dto.UserDTO;
import com.kh.mvc.model.service.MemberService;

public class UserController {

	/**
	 * View에서 온 요청을 받아 요청을 처리해주는 클래스.
	 * 메소드로 전달된 데이터값을 가공처리한 후 DAO로 전달
	 * DAO로부터 반환받은 결과를 사용자가 보게될 View(응답화면)에 반환.
	 * 
	 */
	
	private UserDAO userDao = new UserDAO();
	private MemberService userService = new MemberService();
	
	
	
	public List<UserDTO> findAll() {
		
		return userService.findAll();
		
	}
	
	
	public int insertUser(String userId, String userPw, String UserName) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userPw);
		user.setUserName(UserName);
		
		
		int result = userDao.insertUser(user);
		user = null;
		return result;
	}
	
	
	
	
	public int updatePw(String userId, String userNpw) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		user.setUserPw(userNpw);
		
		int result = userDao.updatePw(user);
		user = null;
		return result;
		
	}
	
	
	
	public int deleteUser(int userNo) {
		UserDTO user = new UserDTO();
		user.setUserNo(userNo);
		
		int result = userDao.deleteUser(user);
		user = null;
		
		return result;
	}


	
	public List<UserDTO> noSelect(int userNo) {
		UserDTO user = new UserDTO();
		user.setUserNo(userNo);
		
		List<UserDTO> result = userDao.noSelect(user);
		user = null;
		
		return result;
		
	}


	public List<UserDTO> idSerach(String userId) {
		UserDTO user = new UserDTO();
		user.setUserId(userId);
		
		List<UserDTO> result = userDao.idSerach(user);
		user = null;
		
		return result;
	}
	
	
	
	
	
	
	
	
	
}
