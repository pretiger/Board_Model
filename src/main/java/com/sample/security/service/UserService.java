package com.sample.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sample.security.dao.UserDAO;
import com.sample.security.model.UserDTO;

@Service
public class UserService {

	@Autowired
	UserDAO userDao;
	
	public void insert(UserDTO user) {
		userDao.insert(user);
	}
	
	public UserDTO loginchk(UserDTO user) {
		return userDao.loginchk(user);
	}
}
