package com.simplilearn.phase2endproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.simplilearn.phase2endproject.model.User;
import com.simplilearn.phase2endproject.repository.UserRepository;
import com.simplilearn.phase2endproject.service.UserService;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findUserByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("No matching user!");
		}
		return new CustomUserDetails(user);
	}

}
