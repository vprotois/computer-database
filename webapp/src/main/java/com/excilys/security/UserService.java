package com.excilys.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.excilys.dao.DAOUser;

public class UserService implements UserDetailsService {

	@Autowired
	private DAOUser dao;
	
	private Logger log = LoggerFactory.getLogger(UserService.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.debug("username : "+username);
		Optional<com.excilys.model.User> user = dao.getUserByName(username);
		log.debug("user from base : "+user);
		if (!user.isPresent()) {
			throw new UsernameNotFoundException("User not found");
		}
		
        List<GrantedAuthority> authorities = getAuth(user);
		
	    return buildUser(user, authorities);
	        
	}

	private User buildUser(Optional<com.excilys.model.User> user, List<GrantedAuthority> authorities) {
		return new User(user.get().getLogin(), user.get().getPassword(), true, true, true, true, authorities);
	}

	private List<GrantedAuthority> getAuth(Optional<com.excilys.model.User> user) {
		
		  List<GrantedAuthority> setAuths = new ArrayList<GrantedAuthority>();
		  setAuths.add(new SimpleGrantedAuthority("USER"));
		  
		  if(user.get().getRole() == 1) {
			  setAuths.add(new SimpleGrantedAuthority("MANAGER"));
		  }

		return setAuths;
	}

}
