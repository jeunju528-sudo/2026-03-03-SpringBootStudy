package com.sist.web.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sist.web.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	private final CustomUserDetailsService userDetailsService;
	private final JwtAuthenticationProvider provider;
	
	public JwtAuthenticationFilter(CustomUserDetailsService customUserDetailsService, JwtAuthenticationProvider jwtAuthenticationProvider) {
		this.userDetailsService = customUserDetailsService;
		this.provider = jwtAuthenticationProvider;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// 프론트에서 넘어오는 token 값, 처음 로그인할때는 없을 수도 있음
		String token = null;
		// http header에서 넘어오는 인증값을 담는 영역 : Authorization
		// {Authorization: Bearer adfadfadfadfadfad....}
		String header = request.getHeader("Authorization");
		if(header != null && header.startsWith("Bearer ")) {
			token = header.substring(7);
		}
		
		if(token == null && request.getCookies() != null) {
			for(Cookie cookie : request.getCookies()) {
				if("accessToken".equals(cookie.getName())) {
					token = cookie.getValue();
					break;
				}
			}
		}
		
		if(token != null && provider.validate(token)) {
			String username = provider.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
			
			// 인증정보 저장
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
	}
	
}
