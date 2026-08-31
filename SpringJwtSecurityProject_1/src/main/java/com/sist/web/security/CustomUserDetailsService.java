package com.sist.web.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// 사용자 조회
public class CustomUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.equals("admin")) {
			return User.builder()
					.username("admin")
					.password("{noop}1234")
					.roles("ADMIN")
					.build();
		}
		return User.builder()
				.username("user")
				.password("{noop}1234")
				.roles("USER")
				.build();
	}

}
