package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.web.service.EmpService;
import com.sist.web.vo.EmpVO;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmpController {
	
	private final EmpService empService;
	
	@GetMapping("/emp/list")
	public String emp_list(Model model) {
		
		List<EmpVO> list = empService.empListData();
		model.addAttribute("list", list);
		
		return "emp/list";
	}
}
