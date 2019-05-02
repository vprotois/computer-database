package com.excilys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dao.DAOUser;
import com.excilys.model.User;

@Service
public class UserService {

	@Autowired
	private DAOUser dao;
	
	public void createUser(String username,String password,Integer role) {
		User newUser = new User();
			newUser.setLogin(username);
			newUser.setPassword(password);
			newUser.setRole(role);
		dao.createUser(newUser);
	}
	
}
