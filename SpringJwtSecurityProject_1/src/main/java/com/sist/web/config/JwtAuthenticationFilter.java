package com.sist.web.config;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

	private final UserDetailsService userDetailsService;
	private final JwtTokenProvider jwtTokenProvider;

	public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtTokenProvider jwtTokenProvider) {
		this.userDetailsService = userDetailsService;
		this.jwtTokenProvider = jwtTokenProvider;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			String token = header.substring(7); // JWT TOKEN payload에서 앞에 식별자 7글자 자름, 그러면 validation 나옴
			if (jwtTokenProvider.validate(token)) { // token이 유효하면
				String username = jwtTokenProvider.getUsername(token);
				UserDetails user = userDetailsService.loadUserByUsername(username);
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(username, null,
						user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth); // Security에 저장
			}
		}
		
		try {
			filterChain.doFilter(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
