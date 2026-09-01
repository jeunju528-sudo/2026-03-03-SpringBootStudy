package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.AuthorityVO;

@Mapper
@Repository
public interface AuthorityMapper {
	@Select("SELECT userid, authority "
			+ "FROM authority "
			+ "WHERE userid = #{userid}")
	public List<AuthorityVO> getAuthorityData(@Param("userid") String userid);
}
