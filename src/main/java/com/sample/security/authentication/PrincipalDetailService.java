package com.sample.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sample.security.dao.UserDAO;
import com.sample.security.model.UserDTO;

public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	UserDAO userDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userDao.login(username);
		if(user == null) throw new UsernameNotFoundException(username);
		return new PrincipalDetail(user);
	}

}
