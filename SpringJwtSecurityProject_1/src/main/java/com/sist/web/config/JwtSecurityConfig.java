package com.sist.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig {
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
		return new JwtAuthenticationFilter(userDetailsService, jwtTokenProvider);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter filter) throws Exception{
		
		http
			.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // 세션을 사용하지 않음
			.authorizeHttpRequests(auth -> auth.requestMatchers("/", "/member/login").permitAll()
												.requestMatchers("/admin").hasRole("ADMIN")
												.anyRequest().permitAll())
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
	
}
