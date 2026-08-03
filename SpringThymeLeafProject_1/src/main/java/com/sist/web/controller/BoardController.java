package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sist.web.entity.BoardEntity;
import com.sist.web.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("board/")
public class BoardController {
	private final BoardService service;
	
	@GetMapping("list")
	public String board_list(@RequestParam(name="page", required = false) String page, Model model) {
		
		if(page == null) {
			page = "1";
		}

		int curpage = Integer.parseInt(page);
		int start = (curpage-1)*10;
		
		List<BoardEntity> list = service.boardListData(start);
		
		int count = service.boardCount();
		int totalpage = (int)(Math.ceil(count/10.0));
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		return "board/list";
	}
}
