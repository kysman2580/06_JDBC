package com.kh.mvc2.model.dto;

import java.sql.Date;

public class UserDTO2 { // 한 행을 담을 객체

	private String empId;
	private String empName;
	private String empNo;
	private String Email;
	private String Phone;

	public UserDTO2() {
		super();
	}

	public UserDTO2(String empId, String empName, String empNo, String email, String phone) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empNo = empNo;
		Email = email;
		Phone = phone;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhone() {
		return Phone;
	}

	public void setPhone(String phone) {
		Phone = phone;
	}

	@Override
	public String toString() {
		return "UserDTO2 [empId=" + empId + ", empName=" + empName + ", empNo=" + empNo + ", Email=" + Email
				+ ", Phone=" + Phone + "]";
	}

}
