package persistance;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Computer;



public class DAOcomputer extends DAOentity{

	public DAOcomputer() {
		super();

	}
	
	
	public List<Computer> listComputers() throws SQLException {
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer;";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		List<Computer> list = new ArrayList<Computer>();
		while (results.next()) {
			list.add(new Computer(results.getLong("id"),
					results.getString("name"), null,
					results.getTimestamp("introduced"),
					results.getTimestamp("discontinued"),
					results.getLong("company_id")
					));
		}
		return list;
	}

	
	public Computer getCompDetails(Long id) throws SQLException {
		String query = "SELECT * FROM computer WHERE id ="+id.longValue()+";";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		while (results.next()) {
			return new Computer(results.getLong("id"),
					results.getString("name"), null,
					results.getTimestamp("introduced"),
					results.getTimestamp("discontinued"),
					results.getLong("company_id")
					);
		}
		return null;
	}
	
	public void createComputer(Computer c) throws SQLException {
		
		PreparedStatement prep = conn.prepareStatement(
				"INSERT INTO computer "
				+ "(id, name, introduced, discontinued,company_id) VALUES "
				+ "(?,?,?,?,?);");
		prep.setLong(1,c.getId());
		prep.setString(2,c.getName());
		prep.setTimestamp(3,c.getIntroduced());
		prep.setTimestamp(4,c.getDiscontinued());
		prep.setLong(5,c.getCompany().getId().longValue());
		prep.executeUpdate();
	}
	
	public void updateComputer(Long id,String colonne, String value) throws SQLException {
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer WHERE id ="+id.longValue()+";";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		if (results.next()) {
			PreparedStatement prep1 = conn.prepareStatement("UPDATE computer SET "+colonne+"=? WHERE id = ?;");
			prep1.setString(1,colonne);
			prep1.setString(2,value);
			prep1.setLong(3,id);
			prep1.executeUpdate();
		}
	}
	
	public void deleteComputer(Long id) throws SQLException {
		String query = "DELETE FROM computer WHERE id ="+id.longValue()+";";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

}
