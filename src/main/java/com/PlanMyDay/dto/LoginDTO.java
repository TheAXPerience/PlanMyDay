package com.PlanMyDay.dto;

import jakarta.validation.constraints.NotEmpty;

public class LoginDTO {
	@NotEmpty(message = "Username must not be empty.")
	private String username;
	@NotEmpty(message = "Password must not be empty.")
	private String password;
	private boolean error = false;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isError() {
		return error;
	}
	public void setError(boolean error) {
		this.error = error;
	}
}
