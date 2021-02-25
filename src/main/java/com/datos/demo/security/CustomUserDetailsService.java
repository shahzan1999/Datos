package com.datos.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.datos.demo.dao.UserRepository;
import com.datos.demo.model.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User userName = userRepo.findByUname(username);
		if(userName == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(userName);
	}

}
