package com.PlanMyDay.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.PlanMyDay.dto.RegistrationDTO;
import com.PlanMyDay.exceptions.EmailAlreadyExistsException;
import com.PlanMyDay.exceptions.UsernameTakenException;
import com.PlanMyDay.models.Account;
import com.PlanMyDay.repositories.UserRepository;
import com.PlanMyDay.services.RegisterUserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Controller
public class UserController {
	@Autowired
	private RegisterUserService registerUserService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Resource(name="authenticationManager")
    private AuthenticationManager authManager;
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("registrationDTO", new RegistrationDTO());
		model.addAttribute("register_complete_link", "/register/submit");
		return "users/register-form";
	}
	
	@PostMapping("/register/submit")
	public String registration(@Valid RegistrationDTO registrationDTO, BindingResult bindingResult, Model model) {
		// System.out.println(registrationDTO.getEmail() + "\n" + registrationDTO.getUsername() + "\n" + registrationDTO.getPassword());
		if (bindingResult.hasErrors()) {
			model.addAttribute("registrationDTO", registrationDTO);
			return "users/register-form";
		}
		
		try {
			registerUserService.register(registrationDTO);
		} catch (UsernameTakenException e) {
			bindingResult.rejectValue("username", "registrationDTO.username", "An account with this username already exists.");
			model.addAttribute("registrationDTO", registrationDTO);
			return "users/register-form";
		} catch (EmailAlreadyExistsException e) {
			bindingResult.rejectValue("email", "registrationDTO.email", "An account with this email already exists.");
			model.addAttribute("registrationDTO", registrationDTO);
			return "users/register-form";
		}
		
		return "redirect:/register/complete";
	}
	
	@GetMapping("/register/complete")
	public String register_complete(Model model) {
		// model.addAttribute("username", principal.getName());
		model.addAttribute("user_list", this.userRepository.findAll());
		return "users/register-complete";
	}
	
	@GetMapping("/login")
	public String login(Model model) {
		return "users/login-form";
	}
	
	/*
	@PostMapping("/login")
	public String handle_login(HttpServletRequest req, @RequestParam("username") String username, @RequestParam("password") String password) {
		try {
			UsernamePasswordAuthenticationToken authReq = new UsernamePasswordAuthenticationToken(username, password);
			Authentication auth = authManager.authenticate(authReq);
			SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
			
			HttpSession session = req.getSession(true);
	        session.setAttribute("SPRING_SECURITY_CONTEXT", sc);
		} catch (BadCredentialsException e) {
			return "redirect:/login?error=true";
		}
		
		return "redirect:/home";
	} */
	
	@PostMapping("/logout")
	public String handle_logout(Model model) {
		// TODO: logout
		return "redirect:/login?logout=true";
	}
}
