package com.sist.web.service;

import java.util.List;

import com.sist.web.entity.BoardEntity;

public interface BoardService {
	public BoardEntity findByNo(int no);

	public List<BoardEntity> boardListData(int start);

	public void boardUpdate(BoardEntity vo);

	public void boardInsert(BoardEntity vo);

	public void boardDelete(BoardEntity vo);
	
	public int boardCount();
}
