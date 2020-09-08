package com.sample.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.security.dao.BoardDAO;
import com.sample.security.model.BoardDTO;

@Service
public class BoardService {

	@Autowired
	BoardDAO boardDao;
	
	public BoardDTO detail(int num) {
		return boardDao.detail(num);
	}
	
	public List<BoardDTO> list() {
		return boardDao.list();
	}
}
