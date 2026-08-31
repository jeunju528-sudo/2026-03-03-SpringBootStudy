package com.sist.web.config;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	// 실무에서는 자동 설정되도록 함
	// application => jwt: secret: {JWT_SECRET}
	private final String SECRET = "my-secret-key-my-secret-key"; // 토큰 발급할 때 쓰는 키, 내 마음대로 선언하면 됨
	public String createToken(String username, String role) {
		return Jwts.builder()
				.setSubject(username) // 사용자 ID 저장 {sub: admin}
				.claim("role", role) // 권한 {role: ROLE_ADMIN}
				.setIssuedAt(new Date()) // 발행시간
				.setExpiration(new Date(System.currentTimeMillis()+36000000)) // 유효기간
				.signWith(Keys.hmacShaKeyFor(SECRET.getBytes())) // SECRET 키를 사용하여 JWT에 서명
				.compact();
	}

	// username 
	public String getUsername(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(SECRET.getBytes())
				.build()
				.parseClaimsJwt(token)
				.getBody()
				.getSubject();
	}
	
	// 토큰이 유효한지 확인하는 함수
	public boolean validate(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(SECRET.getBytes())
				.build()
				.parseClaimsJwt(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
}
