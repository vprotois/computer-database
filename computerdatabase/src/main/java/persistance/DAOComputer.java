package persistance;


import java.util.List;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.hibernate.HibernateDeleteClause;
import com.querydsl.jpa.hibernate.HibernateQuery;
import com.querydsl.jpa.hibernate.HibernateQueryFactory;

import exception.CreateComputerError;
import exception.UpdateComputerError;

import java.util.Optional;

import model.Computer;
import model.QComputer;


@Repository
public class DAOComputer {

	private static Logger log= LoggerFactory.getLogger(DAOComputer.class);

	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	
	public Optional<List<Computer>> listComputers(OrderSpecifier<?> order){
		Session session = sessionFactory.getObject().openSession();
		List<Computer> computers = new HibernateQuery<Computer>(session)
										.from(QComputer.computer)
										.orderBy(order)
										.fetch();
		session.close();
		return Optional.ofNullable(computers);
		
	}
	

	public Optional<Computer> getCompDetails(Long id){
		Session session = sessionFactory.getObject().openSession();
		List<Computer> computer = new HibernateQuery<Computer>(session)
											.from(QComputer.computer)
											.where(QComputer.computer.id.eq(id))
											.fetch();
		session.close();
		return Optional.of(computer.get(0));
	}


	public Optional<List<Computer>> getListFromName(String name,OrderSpecifier<?> order){
		Session session = sessionFactory.getObject().openSession();
		List<Computer> computers = new HibernateQuery<Computer>(session)
				.from(QComputer.computer)
				.where(QComputer.computer.name.contains(name).or(QComputer.computer.company.name.contains(name)))
				.orderBy(order)
				.fetch();
		session.close();
		return Optional.ofNullable(computers);
	}


	public void createComputer(Computer computer) throws CreateComputerError {
		log.info("CREATE "+computer);
		Session session = sessionFactory.getObject().openSession();
		session.save(computer);
		session.close();
	}

	public void updateComputer(Computer computer) throws UpdateComputerError{
		Session session = sessionFactory.getObject().openSession();
		HibernateQueryFactory query = new HibernateQueryFactory(session);
		query.update(QComputer.computer)
		.where(QComputer.computer.id.eq(computer.getId()))
		.set(QComputer.computer.name, computer.getName())
        .set(QComputer.computer.introduced, computer.getIntroduced())
        .set(QComputer.computer.discontinued, computer.getDiscontinued())
        .set(QComputer.computer.company, computer.getCompany())
        .execute();
		session.close();
	}


	public void deleteComputer(Long id) throws UpdateComputerError{
		Session session = sessionFactory.getObject().openSession();
		new HibernateDeleteClause(session, QComputer.computer)
				.where(QComputer.computer.id.eq(id));
		session.close();
	}
}