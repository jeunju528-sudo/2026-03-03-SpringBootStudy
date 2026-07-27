package com.sist.web.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.web.mapper.EmpMapper;
import com.sist.web.vo.EmpVO;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EmpDAO {

	public final EmpMapper empMapper;
	
	public List<EmpVO> empListData(){
		return empMapper.empListData();
	}
}
