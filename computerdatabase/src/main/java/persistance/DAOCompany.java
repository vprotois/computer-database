package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mapper.EntityMapper;
import model.Company;

public class DAOCompany {
		
	String selectAll ="SELECT id,name FROM company;";
	String selectId = "SELECT id,name FROM company WHERE id = ?;";
	
	public DAOCompany() {
		super();
	}
	
	public Company getCompany(Long id){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet results =  stmt.executeQuery();
			stmt.setLong(1,id);
			return EntityMapper.mapSingleCompany(results);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;		
	}

	public List<Company> getCompanies(){
		List<Company> companies = new ArrayList<Company>();
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet results =  stmt.executeQuery();
			companies = EntityMapper.mapCompaniesList(results);
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return companies;			

	}
}
