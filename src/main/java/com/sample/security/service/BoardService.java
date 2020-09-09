package com.sample.security.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.security.dao.BoardDAO;
import com.sample.security.model.BoardDTO;

@Service
public class BoardService {

	@Autowired
	BoardDAO boardDao;
	
	@Transactional
	public void insertBoard(BoardDTO board) {
		boardDao.insertBoard(board);
	}
	
	@Transactional(readOnly = true)
	public int count() {
		return boardDao.count();
	}
	
	@Transactional(readOnly = true)
	public BoardDTO detail(int num) {
		return boardDao.detail(num);
	}
	
	@Transactional(readOnly = true)
	public List<BoardDTO> list(int start, int end) {
		Map<String, Object> map = new HashMap<>();
		map.put("start", start);
		map.put("end", end);
		return boardDao.list(map);
	}
}
