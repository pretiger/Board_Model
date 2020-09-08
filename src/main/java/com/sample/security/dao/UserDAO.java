package com.sample.security.dao;

import com.sample.security.model.UserDTO;

public interface UserDAO {

	public UserDTO loginchk(UserDTO user);
	public void update(UserDTO user);
	public UserDTO login(String username);
	public void insert(UserDTO user);
}
