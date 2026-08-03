package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.entity.BoardEntity;
import com.sist.web.repository.BoardRepository;
import com.sist.web.vo.BoardDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardRepository respository;

	@Override
	public BoardEntity findByNo(int no) {
		return respository.findByNo(no);
	}

	@Override
	public List<BoardDTO> boardListData(int start) {
		return respository.boardListData(start);
	}

	@Override
	public void boardUpdate(BoardEntity vo) {
		respository.save(vo);
	}

	@Override
	public void boardInsert(BoardEntity vo) {
		respository.save(vo);
	}

	@Override
	public void boardDelete(BoardEntity vo) {
		respository.delete(vo);
	}

	@Override
	public int boardCount() {
		return (int) respository.count();
	}

}
