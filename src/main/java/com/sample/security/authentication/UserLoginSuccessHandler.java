package com.sample.security.authentication;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class UserLoginSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger =LoggerFactory.getLogger(UserLoginSuccessHandler.class);
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		PrincipalDetail dto=(PrincipalDetail)authentication.getPrincipal();
		
		logger.info("=========User 내용 확인 : "+dto.getUser());
		logger.info("===========Authentication 내용 확인"+authentication.getPrincipal().getClass());
//		String msg=authentication.getName()+"님 환영합니다.";
//		request.setAttribute("msg", msg);
//		RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/views/home.jsp");
//		rd.forward(request, response);
//		logger.info("http 내역 : "+request.getContextPath()+"/");
	}

}
