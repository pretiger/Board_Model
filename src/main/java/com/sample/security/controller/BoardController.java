package com.sample.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.security.model.BoardDTO;
import com.sample.security.model.ResponseDTO;
import com.sample.security.service.BoardService;
import com.sample.security.utils.Pager;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@ResponseBody
	@PostMapping("/board/insert")
	public ResponseDTO<String> insert(@RequestBody BoardDTO board) {
		boardService.insertBoard(board);
		return new ResponseDTO<String>(HttpStatus.OK.value(), "Insert Success!");
	}
	
	@GetMapping("/board/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	@GetMapping("/detailForm/{num}")
	public String detailForm(@PathVariable int num, Model model) {
		model.addAttribute("dto", boardService.detail(num));
		return "board/detailForm";
	}
	
	@GetMapping({"/","/auth/list"})
	public String index(Model model, @RequestParam(defaultValue = "1") int curPage) {
		int count = boardService.count();
		Pager pager = new Pager(count, curPage);
		int start = pager.getPageStart() - 1;
		int end = Pager.getBlockScale();
		model.addAttribute("dto", boardService.list(start, end));
		model.addAttribute("page", pager);
		return "index";
	}
}
