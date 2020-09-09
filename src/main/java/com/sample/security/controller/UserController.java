package com.sample.security.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sample.security.authentication.PrincipalDetail;
import com.sample.security.authentication.PrincipalDetailService;
import com.sample.security.model.ResponseDTO;
import com.sample.security.model.UserDTO;
import com.sample.security.service.UserService;

@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserService userService;
	
	@Autowired
	PrincipalDetailService authService;
	
	@ResponseBody
	@PutMapping("/user/update")
	public ResponseDTO<String> update(@RequestBody UserDTO user) {
		userService.update(user);
		UserDetails principal = authService.loadUserByUsername(user.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDTO<String>(HttpStatus.OK.value(), "User update Success!");
	}
	
	@GetMapping("/user/userInfo")
	public String userInfo(Model model, Authentication auth) {
		PrincipalDetail principal = (PrincipalDetail)auth.getPrincipal();
		model.addAttribute("dto", userService.login(principal.getUsername()));
		return "user/detailForm";
	}
	
	@GetMapping("/auth/denied")
	public String denied(Model model) {
		model.addAttribute("message", "You has not a role!");
		return "user/deniedForm";
	}
	
//	@ResponseBody
//	@GetMapping("/auth/denied")
//	public ResponseDTO<String> denied() {
//		return new ResponseDTO<String>(HttpStatus.NOT_ACCEPTABLE.value(), "You has not a role!");
//	}
	
	@ResponseBody
	@GetMapping("/auth/loginSuccess")
	public ResponseDTO<String> loginSuccess(Authentication auth) {
		PrincipalDetail principal = (PrincipalDetail)auth.getPrincipal();
		return new ResponseDTO<String>(HttpStatus.OK.value(), principal.getUsername());
	}
	
	@ResponseBody
	@GetMapping("/auth/loginError")
	public ResponseDTO<String> loginError() {
		return new ResponseDTO<String>(HttpStatus.NOT_FOUND.value(), "Username or Password mismatch!");
	}
	
	@ResponseBody
	@PostMapping("/auth/insert")
	public ResponseDTO<String> insert(@RequestBody UserDTO user) {
		userService.insert(user);
		UserDetails principal = authService.loadUserByUsername(user.getUsername());
		Authentication authentication = new UsernamePasswordAuthenticationToken(principal, principal.getPassword(), principal.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new ResponseDTO<String>(HttpStatus.OK.value(), "user insert seccess!");
	}
	
	@ResponseBody
	@GetMapping("/logout")
	public ResponseDTO<String> logout(HttpSession session) {
		session.invalidate();
		return new ResponseDTO<String>(HttpStatus.OK.value(), "logout!");
	}
	
//	General Login Process
//	@ResponseBody
//	@PostMapping("/auth/login")
//	public ResponseDTO<UserDTO> login(@RequestBody UserDTO user, HttpSession session) {
//		UserDTO result = userService.loginchk(user);
//		if(result != null) {
//			session.setAttribute("username", result.getUsername());
//			return new ResponseDTO<UserDTO>(HttpStatus.OK.value(), result);
//		}
//		return new ResponseDTO<UserDTO>(HttpStatus.NOT_FOUND.value(), result);
//	}
	
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
}
