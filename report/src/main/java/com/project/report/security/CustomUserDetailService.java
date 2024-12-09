package com.project.report.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.project.report.entity.User;
import com.project.report.exception.ReportException;
import com.project.report.repository.UserRepo;



public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub		
		User user = userRepo.findByUserName(username).orElseThrow(() -> new ReportException("User not found !!"));
		return user;
	}

}
