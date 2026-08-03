package com.sist.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sist.web.entity.BoardEntity;
import com.sist.web.service.BoardService;
import com.sist.web.vo.BoardDTO;

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
		
		List<BoardDTO> list = service.boardListData(start);
		
		int count = service.boardCount();
		int totalpage = (int)(Math.ceil(count/10.0));
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		return "board/list";
	}
	
	@GetMapping("detail")
	public String board_detail(@RequestParam(name="no") int no, Model model) {
		
		BoardEntity vo = service.findByNo(no);
		vo.setHit(vo.getHit()+1);
		service.boardUpdate(vo);
		
		vo = service.findByNo(no);
		model.addAttribute("vo", vo);
		
		return "board/detail";
	}
	
	@GetMapping("insert")
	public String board_insert() {
		return "board/insert";
	}
	
	/*
	 * 파라미터 단위로 받을 때 : @RequestParam(name="no") int no
	 * 모델 단위로 받을 때 : @ModelAttribute("vo") BoardEntity vo
	 * */
	@PostMapping("insert_ok")
	public String board_insert_ok(@ModelAttribute("vo") BoardEntity vo) {
		service.boardInsert(vo);
		return "redirect:/board/list";
	}
	
	@GetMapping("delete")
	public String board_delete(@RequestParam(name="no")int no, Model model) {
		model.addAttribute("no", no);
		return "board/delete";
	}
	
	@PostMapping("delete_ok")
	public String board_delete_ok(@RequestParam(name="no") int no, @RequestParam(name="pwd") String pwd, Model model) {
		
		String res = "no";
		BoardEntity vo = service.findByNo(no);
		if(vo.getPwd().equals(pwd)) {
			res = "yes";
			service.boardDelete(vo);
		}
		model.addAttribute("res", res);
		return "board/delete_ok";
	}
	
	@GetMapping("update")
	public String board_update(@RequestParam(name="no")int no, Model model) {
		BoardEntity vo = service.findByNo(no);
		model.addAttribute("vo", vo);
		return "board/update";
	}
	
	@PostMapping("update_ok")
	public String board_update_ok(@ModelAttribute("vo")BoardEntity vo, Model model) {
		String res = "no";
		BoardEntity board = service.findByNo(vo.getNo());
		if(board.getPwd().equals(vo.getPwd())) {
			vo.setHit(board.getHit());
			service.boardUpdate(vo);
			res = "yes";
		}
		model.addAttribute("res", res);
		model.addAttribute("no", vo.getNo());
		return "board/update_ok";
	}
}
