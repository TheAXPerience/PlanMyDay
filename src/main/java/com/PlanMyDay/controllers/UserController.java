package com.PlanMyDay.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.PlanMyDay.dto.RegistrationDTO;
import com.PlanMyDay.models.TestUser;
import com.PlanMyDay.repositories.TestUserRepository;

@Controller
public class UserController {
	private final TestUserRepository testUserRepository;
	
	public UserController(TestUserRepository testUserRepository) {
		this.testUserRepository = testUserRepository;
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("registrationDTO", new RegistrationDTO());
		model.addAttribute("register_complete_link", "/register/submit");
		return "users/register-form";
	}
	
	@PostMapping("/register/submit")
	public String registration(RegistrationDTO registrationDTO) {
		// System.out.println(registrationDTO.getEmail() + "\n" + registrationDTO.getUsername() + "\n" + registrationDTO.getPassword());
		
		// TODO: validate inputs
		TestUser user = new TestUser();
		user.setEmail(registrationDTO.getEmail());
		user.setUsername(registrationDTO.getUsername());
		user.setPassword(registrationDTO.getPassword());
		this.testUserRepository.save(user);
		
		// TODO: check against existing users
		// TODO: sign in client to new user
		// TODO: send to landing page (/home)
		return "redirect:/register/complete";
	}
	
	@GetMapping("/register/complete")
	public String register_complete(Model model) {
		model.addAttribute("user_list", this.testUserRepository.findAll());
		return "users/register-complete";
	}
}
