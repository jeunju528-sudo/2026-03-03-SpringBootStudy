package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.MemberVO;

@Mapper
@Repository
public interface MemberMapper {
	@Select("SELECT userid, username, userpwd, enable, sex "
			+ "FROM springmember "
			+ "WHERE userid = #{userid}")
	public MemberVO findByUserId(@Param("userid") String userid);
		
}
