package com.PlanMyDay.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.PlanMyDay.models.Account;
import com.PlanMyDay.repositories.UserRepository;
import java.util.ArrayList;
@Service
public class LoginUserDetailsService implements UserDetailsService {
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account user = userRepository.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(user.getEmail(), user.getPassword(), true, true, true, true, new ArrayList<>());
		/*
		UserDetails userDetails = User.withUsername(user.getEmail())
				.password(user.getPassword())
				.authorities("USER")
				.accountLocked(false)
				.accountExpired(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
		return userDetails; */
	}
}
