package com.sample.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sample.security.dao.UserDAO;
import com.sample.security.model.UserDTO;

@Service
public class UserService {

	@Autowired
	UserDAO userDao;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	public void update(UserDTO user) {
		String bcPassword = encoder.encode(user.getPassword());
		user.setPassword(bcPassword);
		userDao.update(user);
	}
	
	public UserDTO login(String username) {
		return userDao.login(username);
	}
	
	public void insert(UserDTO user) {
		String bcPassword = encoder.encode(user.getPassword());
		user.setPassword(bcPassword);
		userDao.insert(user);
	}
	
	public UserDTO loginchk(UserDTO user) {
		return userDao.loginchk(user);
	}
}
