package persistance;
import java.sql.*;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exception.CreateComputerError;
import exception.UpdateComputerError;

import java.util.Optional;

import mapper.ComputerMapper;
import model.Computer;




public class DAOComputer {

	private static Logger log= LoggerFactory.getLogger(DAOComputer.class);
	
	public DAOComputer() {
		super();
	}
	
	private static String selectAll = "SELECT cr.id, cr.name, cr.introduced, cr.discontinued, cr.company_id, cy.name FROM computer as cr "
			+ "LEFT JOIN company as cy ON cr.company_id=cy.id ";
	private static String selectCompWithId  = selectAll + "WHERE cr.id =?";
	private static String selectCompWithName  = selectAll + "WHERE cr.name=?";
	private static String InsertComputerWithId = "INSERT INTO computer "
			+ "(id, name, introduced, discontinued,company_id) VALUES "
			+ "(?,?,?,?,?);";
	private static String InsertComputer = "INSERT INTO computer "
			+ "(name, introduced, discontinued,company_id) VALUES "
			+ "(?,?,?,?);";
	private static String update = "UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id = ?";
	private static String delete = "DELETE FROM computer WHERE id =?";

	public Optional<List<Computer>> listComputers(){
		try (Connection conn = DAOFactory.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(selectAll);
			return Optional.of(ComputerMapper.mapComputerList(results));
		}catch(SQLException e){
			System.out.println(e.getMessage());
			log.error("Error when getting the computer List");
		}
		return Optional.empty();
	}


	public Optional<Computer> getCompDetails(Long id){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectCompWithId);
			stmt.setLong(1,id);
			ResultSet results = stmt.executeQuery();
			return ComputerMapper.mapSingleComputer(results);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when getting the details of computer : "+id);
		}
		return Optional.empty();		
	}
	
	public Optional<List<Computer>> getListFromName(String name){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectCompWithName);
			stmt.setString(1,name);
			ResultSet results = stmt.executeQuery();
			return Optional.of(ComputerMapper.mapComputerList(results));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when getting the list of computer with name : "+name);
		}
		return Optional.empty();		
	}

	public void createComputer(Computer c) throws CreateComputerError {
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement prep;
			if(c.getId() != null) {
				prep = conn.prepareStatement(InsertComputerWithId);
				fillStatementInsertWithId(c, prep);
			}
			else {
				prep = conn.prepareStatement(InsertComputer);
				fillStatementInsert(c, prep);
			}
			prep.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when creating computer : "+ c);
			throw new CreateComputerError();
		}

	}


	private void fillStatementInsertWithId(Computer c, PreparedStatement prep) throws SQLException {
		prep.setLong(1,c.getId());
		prep.setString(2,c.getName());
		prep.setTimestamp(3,c.getIntroduced());
		prep.setTimestamp(4,c.getDiscontinued());
		prep.setLong(5,c.getCompanyId());
	}
	
	private void fillStatementInsert(Computer c, PreparedStatement prep) throws SQLException {
		prep.setString(1,c.getName());
		prep.setTimestamp(2,c.getIntroduced());
		prep.setTimestamp(3,c.getDiscontinued());
		prep.setLong(4,c.getCompanyId());
	}

	public void updateComputer(Computer c) throws UpdateComputerError{
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement prep = conn.prepareStatement(update);
			fillStatementUpdate(c, prep);
			prep.executeUpdate();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when updating computer : "+c);
			throw new UpdateComputerError();
		}
	}


	private void fillStatementUpdate(Computer c, PreparedStatement prep) throws SQLException {
		prep.setString(1,c.getName());
		prep.setTimestamp(2,c.getIntroduced());
		prep.setTimestamp(3,c.getDiscontinued());
		prep.setLong(4,c.getCompanyId());
		prep.setLong(5,c.getId());
	}

	public boolean deleteComputer(Long id){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(delete);
			stmt.setLong(1,id);
			stmt.executeUpdate();
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when deleting computer : "+ id);
			return false;
		}
	}

}
