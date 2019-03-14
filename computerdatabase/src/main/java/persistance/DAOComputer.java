package persistance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Computer;
import model.ComputerBuilder;



public class DAOComputer extends DAOEntity{

	public DAOComputer() {
		super();

	}
	
	private static String selectAllComp = "SELECT id,name,introduced,discontinued,company_id FROM computer;";
	private static String selectCompWithId  = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id =?;";
	private static String InsertComputer = "INSERT INTO computer "
			+ "(id, name, introduced, discontinued,company_id) VALUES "
			+ "(?,?,?,?,?);";
	private static String update = "UPDATE computer SET name=?,introduced=?,discontinued=?,company_id=? WHERE id = ?;";
	private static String delete = "DELETE FROM computer WHERE id =?;";
	
	public List<Computer> listComputers() throws SQLException {
		
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(selectAllComp);
		List<Computer> list = new ArrayList<Computer>();
		while (results.next()) {
			list.add(new ComputerBuilder().withId(results.getLong("id"))
					.withName(results.getString("name"))
					.withIntroduced(results.getTimestamp("introduced"))
					.withDiscontinued(results.getTimestamp("discontinued"))
					.withCompanyId(results.getLong("company_id"))
					.build()
					);
		}
		return list;
	}

	
	public Computer getCompDetails(Long id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(selectCompWithId);
		stmt.setLong(1,id);
		ResultSet results = stmt.executeQuery();
		while (results.next()) {
			return new ComputerBuilder().withId(results.getLong("id"))
					.withName(results.getString("name"))
					.withIntroduced(results.getTimestamp("introduced"))
					.withDiscontinued(results.getTimestamp("discontinued"))
					.withCompanyId(results.getLong("company_id"))
					.build()
					;
		}
		return null;
	}
	
	public void createComputer(Computer c) throws SQLException {
		
		PreparedStatement prep = conn.prepareStatement(InsertComputer);
		prep.setLong(1,c.getId());
		prep.setString(2,c.getName());
		prep.setTimestamp(3,c.getIntroduced());
		prep.setTimestamp(4,c.getDiscontinued());
		prep.setLong(5,c.getCompany().getId().longValue());
		prep.executeUpdate();
	}
	
	public void updateComputer(Computer c) throws SQLException {
			PreparedStatement prep = conn.prepareStatement(update);
			prep.setString(1,c.getName());
			prep.setTimestamp(2,c.getIntroduced());
			prep.setTimestamp(3,c.getDiscontinued());
			prep.setLong(4,c.getCompanyId());
			prep.setLong(5,c.getId());
			prep.executeUpdate();
		}
	
	public void deleteComputer(Long id) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(delete);
		stmt.setLong(1,id);
		stmt.executeUpdate();
	}

}
