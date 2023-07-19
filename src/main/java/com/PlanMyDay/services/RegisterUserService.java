package com.PlanMyDay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.PlanMyDay.dto.RegistrationDTO;
import com.PlanMyDay.exceptions.EmailAlreadyExistsException;
import com.PlanMyDay.exceptions.UsernameTakenException;
import com.PlanMyDay.models.Account;
import com.PlanMyDay.repositories.UserRepository;

@Service
public class RegisterUserService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void register(RegistrationDTO registrationDto) throws UsernameTakenException, EmailAlreadyExistsException{
		if (usernameExists(registrationDto.getUsername())) {
			throw new UsernameTakenException("The given username is already taken.");
		} else if (emailExists(registrationDto.getEmail())) {
			throw new EmailAlreadyExistsException("The given email already has an account");
		}
		
		Account user = new Account();
		user.setUsername(registrationDto.getUsername());
		user.setEmail(registrationDto.getEmail());
		user.setPassword(passwordEncoder.encode(registrationDto.getPassword()));
		this.userRepository.save(user);
	}
	
	private boolean usernameExists(String username) {
		return userRepository.findByUsername(username) != null;
	}
	
	private boolean emailExists(String email) {
		return userRepository.findByEmail(email) != null;
	}
}
