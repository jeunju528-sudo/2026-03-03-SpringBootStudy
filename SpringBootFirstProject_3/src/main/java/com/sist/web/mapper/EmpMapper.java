package com.sist.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.EmpVO;

@Mapper
@Repository
public interface EmpMapper {
	@Select("SELECT empno, ename, job "
			+ "FROM emp")
	public List<EmpVO> empListData();
}
