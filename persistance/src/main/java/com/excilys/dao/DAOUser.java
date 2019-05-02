package com.excilys.dao;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.excilys.exception.InvalidUserException;
import com.excilys.model.QUser;
import com.excilys.model.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DAOUser {

	private static Logger log= LoggerFactory.getLogger(DAOUser.class);

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	private JPAQueryFactory queryFactory;
	
	
	public Optional<List<User>> listUsers(){
		List<User> list = queryFactory
				.selectFrom(QUser.user)
				.fetch();
		return Optional.ofNullable(list);
	}
	
	public Optional<User> getUserByName(String login) {
		log.debug("getting user : " +login);
		User user = queryFactory
				.selectFrom(QUser.user)
				.where(QUser.user.login.eq(login))
				.fetchOne();
		return Optional.ofNullable(user);
	}
	
	public void verifyUser(String login,String password) throws InvalidUserException {
		User user = queryFactory
				.selectFrom(QUser.user)
				.where(QUser.user.login.eq(login)
						.and(QUser.user.password.eq(password)))
				.fetchOne();
		if(user == null) {
			throw new InvalidUserException("User not found or invalid password");
		}
				
	}
	
	public void createUser(User user) {
		log.info("User created : "+user);
		Session session = sessionFactory.getObject().openSession();
		session.save(user);
		session.close();
	}
	
}
