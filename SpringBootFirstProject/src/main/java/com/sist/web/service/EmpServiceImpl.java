package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.dao.EmpDAO;
import com.sist.web.vo.EmpVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmpServiceImpl implements EmpService{
	
	private final EmpDAO dao;

	@Override
	public List<EmpVO> empListData() {
		return dao.empListData();
	}
	
}
