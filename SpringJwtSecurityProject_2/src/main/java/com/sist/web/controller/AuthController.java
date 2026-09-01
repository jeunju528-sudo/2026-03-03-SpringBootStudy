package com.sist.web.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.jwt.JwtAuthenticationProvider;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthenticationManager manager;
	private final JwtAuthenticationProvider provider;
	
	@RequestMapping("/member/login")
	public ResponseEntity<?> login(@RequestParam(value="username", required = false) String username,
								@RequestParam(value="password", required = false) String password){
		
		
		// ID/PW 인증
		Authentication auth = manager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		
		// 인증된 사용자 정보
		UserDetails user = (UserDetails) auth.getPrincipal();
		
		// 권한
		String role = user.getAuthorities().iterator().next().getAuthority();
		
		// JWT 생성
		String token = provider.createToken(user.getUsername(), role);
		
		// Cookie 생성
		ResponseCookie cookie = ResponseCookie.from("accessToken")
													.httpOnly(true)
													.secure(false)
													.path("/")
													.maxAge(3600)
													.build();
		
		return ResponseEntity
				.status(HttpStatus.FOUND)
				.header(HttpHeaders.SET_COOKIE, cookie.toString())
				.header(HttpHeaders.LOCATION,"/home")
				.build();
	}
}
