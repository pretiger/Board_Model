package com.sample.security.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sample.security.dao.UserDAO;
import com.sample.security.model.UserDTO;

@Service
public class UserAuthenticationService implements UserDetailsService {

	@Autowired
	UserDAO userDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDTO user = userDAO.login(username);
		if(user == null) throw new UsernameNotFoundException(username);
		return new PrincipalDetail(user);
	}
	
}
