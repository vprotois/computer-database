package com.excilys.dao;


import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;

import com.excilys.exception.CreateComputerException;
import com.excilys.exception.UpdateComputerException;

import java.util.Optional;

import com.excilys.model.Computer;
import com.excilys.model.QComputer;


@Repository
public class DAOComputer {

	private static Logger log= LoggerFactory.getLogger(DAOComputer.class);

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Autowired
	private JPAQueryFactory queryFactory;


	public Optional<List<Computer>> listComputers(OrderSpecifier<?> order){
		List<Computer> computers = queryFactory
				.selectFrom(QComputer.computer)
				.orderBy(order)
				.fetch();
		return Optional.ofNullable(computers);

	}


	public Optional<Computer> getCompDetails(Long id){
		Computer  computer  = queryFactory
				.selectFrom(QComputer.computer)
				.fetchAll()
				.where(QComputer.computer.id.eq(id))
				.fetchOne();
		return Optional.ofNullable(computer);
	}


	public Optional<List<Computer>> getListFromName(String name,OrderSpecifier<?> order){
		List<Computer> computers = queryFactory
				.selectFrom(QComputer.computer)
				.where(QComputer.computer.name.like("%"+name+"%"))
				.orderBy(order)
				.fetch();
		return Optional.ofNullable(computers);
	}


	public void createComputer(Computer computer) throws CreateComputerException {
		Session session = sessionFactory.getObject().openSession();
		session.save(computer);
		session.close();
	}

	
	@Transactional
	public void updateComputer(Computer computer) throws UpdateComputerException{
		queryFactory
		.update(QComputer.computer)
		.where(QComputer.computer.id.eq(computer.getId()))
		.set(QComputer.computer.name, computer.getName())
		.set(QComputer.computer.introduced, computer.getIntroduced())
		.set(QComputer.computer.discontinued, computer.getDiscontinued())
		.set(QComputer.computer.company, computer.getCompany())
		.execute();
	}

	@Transactional
	public void deleteComputer(Long id) throws UpdateComputerException{
		log.info("DELETE "+id);
		queryFactory
		.delete(QComputer.computer)
		.where(QComputer.computer.id.eq(id))
		.execute();
	}
}