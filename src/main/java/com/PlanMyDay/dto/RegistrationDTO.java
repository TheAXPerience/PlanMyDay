package com.PlanMyDay.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class RegistrationDTO {
	@NotEmpty(message = "Email must not be empty.")
	@Email(message = "Please provide a valid Email.")
	private String email;
	@NotEmpty(message = "Username must not be empty.")
	private String username;
	@NotEmpty(message = "Password must not be empty.")
	private String password;
	
	public RegistrationDTO() {}
	
	public RegistrationDTO(String email, String username, String password) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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
}
