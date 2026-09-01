package com.sist.web.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sist.web.vo.AuthorityVO;
import com.sist.web.vo.MemberVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

	private final MemberService memberService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 사용자 정보 조회
		MemberVO member = memberService.findByUserId(username);
		
		// 사용자가 없는 경우
		if(member == null) {
			throw new UsernameNotFoundException("사용자가 없습니다 : "+username);
		}
		
		// 비활성화 된 계정인 경우
		if(member.getEnable() != 1)
		{
			throw new RuntimeException("비활성화된 계정입니다!");
		}
		
		// 권한 가져오기
		List<AuthorityVO> authorityList = memberService.getAuthorityData(username);
		
		List<SimpleGrantedAuthority> authorities = authorityList.stream()
													.map(a->new SimpleGrantedAuthority(a.getAuthority()))
													.toList();
		
		return User.builder()
				.username(member.getUserid())
				.password(member.getUserpwd())
				.authorities(authorities)
				.build();
	}

}
