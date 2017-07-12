package com.fsk.glc.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fsk.glc.dao.UserRepo;


@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo userRepository;

	@Transactional
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException {
		return userRepository.findByUsername(username);
	}
	
	

}
