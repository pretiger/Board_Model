package com.sample.security.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.sample.security.service.BoardService;

@Controller
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("/detailForm/{num}")
	public String detailForm(@PathVariable int num, Model model) {
		model.addAttribute("dto", boardService.detail(num));
		return "board/detailForm";
	}
	
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("dto", boardService.list());
		return "index";
	}
}
