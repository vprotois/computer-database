package persistance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;

public class DAOcompany extends DAOentity{
		
	String selectAll ="SELECT id,name FROM company;";
	String selectId = "SELECT id,name FROM company WHERE id = ?;";
	
	public DAOcompany() {
		super();
	}
	
	public Company getCompany(Long id) throws SQLException {

		PreparedStatement stmt = conn.prepareStatement(selectAll);
		ResultSet results =  stmt.executeQuery();
		stmt.setLong(1,id);
		while (results.next()) {
			return new Company(
					results.getLong("id"),
					results.getString("name"));
		}
		return null;		
	}

	public List<Company> getCompanies() throws SQLException {
		List<Company> companies = new ArrayList<Company>();

		PreparedStatement stmt = conn.prepareStatement(selectAll);
		ResultSet results =  stmt.executeQuery();

		while (results.next()) {
			companies.add(new Company(
					results.getLong("id"),
					results.getString("name")));
		}
		return companies;			

	}
}
