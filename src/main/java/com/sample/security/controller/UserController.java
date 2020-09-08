package com.sample.security.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.security.model.ResponseDTO;
import com.sample.security.model.UserDTO;
import com.sample.security.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@ResponseBody
	@PostMapping("/user/insert")
	public ResponseDTO<String> insert(@RequestBody UserDTO user) {
		userService.insert(user);
		return new ResponseDTO<String>(HttpStatus.OK.value(), "user insert seccess!");
	}
	
	@ResponseBody
	@GetMapping("/logout")
	public ResponseDTO<String> logout(HttpSession session) {
		session.invalidate();
		return new ResponseDTO<String>(HttpStatus.OK.value(), "logout!");
	}
	
	@ResponseBody
	@PostMapping("/auth/login")
	public ResponseDTO<UserDTO> login(@RequestBody UserDTO user, HttpSession session) {
		UserDTO result = userService.loginchk(user);
		if(result != null) {
			session.setAttribute("username", result.getUsername());
			return new ResponseDTO<UserDTO>(HttpStatus.OK.value(), result);
		}
		return new ResponseDTO<UserDTO>(HttpStatus.NOT_FOUND.value(), result);
	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
}
