package persistance;

import java.sql.SQLException;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import exception.CreateComputerError;
import exception.UpdateComputerError;

import java.util.Optional;

import mapper.ComputerMapper;
import model.Computer;


@Repository
public class DAOComputer {

	private static Logger log= LoggerFactory.getLogger(DAOComputer.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static String selectAll = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cr.company_id, cy.name FROM computer as cr "
			+ "LEFT JOIN company as cy ON cr.company_id=cy.id ";
	private static String selectCompWithId  = selectAll + "WHERE cr.id =?";
	private static String selectCompWithName  = selectAll + "WHERE cr.name LIKE ? OR cy.name LIKE ?";
	private static String InsertComputer = "INSERT INTO computer "
			+ "(id, name, introduced, discontinued,company_id) VALUES "
			+ "(?,?,?,?,?);";
	private static String update = "UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id = ?";
	private static String delete = "DELETE FROM computer WHERE id =?";


	@Transactional
	public Optional<List<Computer>> listComputers(String order){
		Object[] args = {};
		return Optional.ofNullable(
					jdbcTemplate.query(selectAll+order,args,new ComputerMapper())
				);
	}

	@Transactional
	public Optional<Computer> getCompDetails(Long id){
		Object[] args = {id}; 
		try {
			return Optional.of(
						jdbcTemplate.queryForObject(selectCompWithId, args, new ComputerMapper())					
					);
		}catch(EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Transactional
	public Optional<List<Computer>> getListFromName(String name,String order){
		Object[] args = {"%"+name+"%","%"+name+"%"};
		return Optional.ofNullable(
					jdbcTemplate.query(selectCompWithName+order,args,new ComputerMapper())
				);
	}

	@Transactional
	public void createComputer(Computer c) throws CreateComputerError {
		log.debug(""+c);
		try {
			jdbcTemplate.update(InsertComputer,StatementSetterFactory.statementInsertComputer(c));
		} catch (DataAccessException | SQLException e) {
			log.error("Error when creating " + c + " : " +e.getMessage());
			throw new CreateComputerError("Couldn't create "+c);
		} 
	}

	@Transactional
	public void updateComputer(Computer c) throws UpdateComputerError{
		try {
			jdbcTemplate.update(update,StatementSetterFactory.statementUpdateComputer(c));
		} catch (DataAccessException | SQLException e) {
			log.error("Error when creating " + c + " : " +e.getMessage());
			throw new UpdateComputerError("Couldn't update "+c);
		} 
	}

	@Transactional
	public void deleteComputer(Long id) throws UpdateComputerError{
		try {
			jdbcTemplate.update(delete,StatementSetterFactory.statementId(id));
		} catch (DataAccessException e) {
			log.error("Error when creating computer " + id + " : " +e.getMessage());
			throw new UpdateComputerError("Couldn't delete computer "+id);
		} 
	}
}
