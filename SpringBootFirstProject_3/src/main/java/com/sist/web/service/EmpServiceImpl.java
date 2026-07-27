package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.EmpMapper;
import com.sist.web.vo.EmpVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService{
	
	private final EmpMapper empMapper;

	@Override
	public List<EmpVO> empListData() {
		return empMapper.empListData();
	}
	
}
