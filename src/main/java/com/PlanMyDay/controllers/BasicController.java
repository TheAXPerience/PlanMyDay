package com.PlanMyDay.controllers;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasicController {
	@GetMapping("/")
	public String index() {
		return "redirect:/login";
	}
	
	@GetMapping("/home")
	public String home(Model model, Authentication authentication) {
		if (authentication == null) {
			return "redirect:/login?logout=true";
		}
		model.addAttribute("name", authentication.getName());
		return "home";
	}
}
