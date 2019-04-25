package persistance;

import java.util.List;
import java.util.Optional;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.hibernate.HibernateQuery;

import mapper.CompanyMapper;
import model.Company;
import model.Computer;
import model.QCompany;
import model.QComputer;

@Repository
public class DAOCompany {

	private static Logger log = LoggerFactory.getLogger(DAOCompany.class);
	
	@Autowired
	private LocalSessionFactoryBean sessionFactory;

	@Transactional
	public Optional<Company> getCompany(Long id) {
		Session session = sessionFactory.getObject().openSession();
		Company company = new HibernateQuery<Company>(session)
				.from(QCompany.company)
				.where(QCompany.company.id.eq(id))
				.fetchOne();
		session.close();
		return Optional.of(company);
	}


	public Optional<List<Company>> getCompanies() {
		Session session = sessionFactory.getObject().openSession();
		List<Company> company = new HibernateQuery<Company>(session)
				.from(QCompany.company)
				.fetch();
		session.close();
		return Optional.of(company);
	}
	
	public void deleteCompany(Long id) {
		
	}


}
