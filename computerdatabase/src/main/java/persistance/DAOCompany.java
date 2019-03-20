package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.CompanyMapper;
import model.Company;

public class DAOCompany {
		
	private static Logger log= LoggerFactory.getLogger(DAOCompany.class);
	
	private static final String selectAll ="SELECT id,name FROM company;";
	private static final String selectId = "SELECT id,name FROM company WHERE id = ?;";
	
	public DAOCompany() {
		super();
	}
	
	public Optional<Company> getCompany(Long id){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectId);
			stmt.setLong(1,id);
			ResultSet results =  stmt.executeQuery();
			
			return Optional.of(CompanyMapper.mapSingleCompany(results));
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when getting Company :" + id);
		}
		return Optional.empty();		
	}

	public Optional<List<Company>> getCompanies(){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet results =  stmt.executeQuery();
			return Optional.of(CompanyMapper.mapCompaniesList(results));
		}catch(SQLException e){
			System.out.println(e.getMessage());
			log.error("Error when getting company list");
		}
		return Optional.empty();
	}

	
}
