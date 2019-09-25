package com.boot.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RegisterRequest {

	private String username;
	private String fullName;
	private String email;
	private String password;
	private String passwordConfirm;

//	public RegisterRequest() {
//
//	}
//
//	public RegisterRequest(String username, String fullName, String email, String password, String passwordConfirm) {
//		this.username = username;
//		this.fullName = fullName;
//		this.email = email;
//		this.password = password;
//		this.passwordConfirm = passwordConfirm;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getFullName() {
//		return fullName;
//	}
//
//	public void setFullName(String fullName) {
//		this.fullName = fullName;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getPasswordConfirm() {
//		return passwordConfirm;
//	}
//
//	public void setPasswordConfirm(String passwordConfirm) {
//		this.passwordConfirm = passwordConfirm;
//	}

}
