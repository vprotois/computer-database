package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import mapper.CompanyMapper;
import model.Company;
import model.Pages;
import model.PagesBuilder;

public class DAOCompany {
		
	private static Logger log= LoggerFactory.getLogger(DAOCompany.class);
	
	private static final String selectAll ="SELECT id,name FROM company;";
	private static final String selectId = "SELECT id,name FROM company WHERE id = ?;";
	
	public DAOCompany() {
		super();
	}
	
	public Company getCompany(Long id){
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectId);
			stmt.setLong(1,id);
			ResultSet results =  stmt.executeQuery();
			return CompanyMapper.mapSingleCompany(results);
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			log.error("Error when getting Company :" + id);
		}
		return null;		
	}

	public List<Company> getCompanies(){
		List<Company> companies = new ArrayList<Company>();
		try (Connection conn = DAOFactory.getConnection()) {
			PreparedStatement stmt = conn.prepareStatement(selectAll);
			ResultSet results =  stmt.executeQuery();
			companies = CompanyMapper.mapCompaniesList(results);
		}catch(SQLException e){
			System.out.println(e.getMessage());
			log.error("Error when getting company list");
		}
		return companies;
	}
	
	public Pages<Company> pageListCompany(){
		return new PagesBuilder<Company>()
				.withData(getCompanies())
				.build();
	}

	
}
