package com.sist.web.service;

import java.util.List;

import com.sist.web.vo.AuthorityVO;
import com.sist.web.vo.MemberVO;

public interface MemberService {
	public MemberVO findByUserId(String userid);

	public List<AuthorityVO> getAuthorityData(String userid);
}
