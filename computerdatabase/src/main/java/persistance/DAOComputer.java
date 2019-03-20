package persistance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.ComputerMapper;
import model.Computer;




public class DAOComputer {

	private static Logger log= LoggerFactory.getLogger(DAOComputer.class);
	
	public DAOComputer() {
		super();
		
	}

	private static String selectAllComp = "SELECT id,name,introduced,discontinued,company_id FROM computer;";
	private static String selectCompWithId  = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id =?;";
	private static String InsertComputerWithId = "INSERT INTO computer "
			+ "(id, name, introduced, discontinued,company_id) VALUES "
			+ "(?,?,?,?,?);";
	private static String InsertComputer = "INSERT INTO computer "
			+ "(name, introduced, discontinued,company_id) VALUES "
			+ "(?,?,?,?);";
	private static String update = "UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id = ?;";
	private static String delete = "DELETE FROM computer WHERE id =?;";

	public List<Computer> listComputers(){
		List<Computer> list = new ArrayList<Computer>();
		try (Connection conn = DAOFactory.getConnection()) {
			Statement stmt = conn.createStatement();
			ResultSet results = stmt.executeQuery(selectAllComp);
			list = ComputerMapper.mapComputerList(results);
		}catch(SQLException e){
			System.out.println(e.getMessage());
			log.error("Error when getting the computer List");
		}
		return list;
	}


	public Computer getCompDetails(Long id){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectCompWithId);
			stmt.setLong(1,id);
			ResultSet results = stmt.executeQuery();
			return ComputerMapper.mapSingleComputer(results);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when getting the details of computer : "+id);
		}
		return null;		
	}

	public boolean createComputer(Computer c) {
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
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when creating the computer : "+ c);
			return false;
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

	public boolean updateComputer(Computer c){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement prep = conn.prepareStatement(update);
			fillStatementUpdate(c, prep);
			prep.executeUpdate();	
			return true;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when updating computer : "+c);
			return false;
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
