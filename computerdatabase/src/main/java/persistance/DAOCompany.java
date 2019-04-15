package persistance;

import java.util.List;
import java.util.Optional;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import mapper.CompanyMapper;
import model.Company;

@Repository
public class DAOCompany {

	private static Logger log = LoggerFactory.getLogger(DAOCompany.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String selectAll = "SELECT id,name FROM company;";
	private static final String selectId = "SELECT id,name FROM company WHERE id = ?;";
	private static final String deleteFromId = "DELETE FROM company WHERE id = ?;";
	private static final String deleteComputers = "DELETE FROM computer WHERE company_id = ?;";

	public DAOCompany() {
	}

	@Transactional
	public Optional<Company> getCompany(Long id) {
		Object[] args = {id};
		try {
			return Optional.of(	
					jdbcTemplate.queryForObject(selectId,args,new CompanyMapper())
					);
		}catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<List<Company>> getCompanies() {
		Object[] args = {};
		return Optional.of(
				jdbcTemplate.query(selectAll,args,new CompanyMapper())
				);
	}
	
	@Transactional(rollbackFor = {DataAccessException.class})
	public void deleteCompany(Long id) {
		try {
			jdbcTemplate.update(deleteComputers, StatementSetterFactory.statementId(id));
			jdbcTemplate.update(deleteFromId,StatementSetterFactory.statementId(id));
		} catch (DataAccessException e) {
			log.error("Error when deleting company "+id+" : " + e.getMessage()+" \nTransaction was rollbacked");
		}
		
	}


}
