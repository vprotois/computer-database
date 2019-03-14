package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.CompanyFactory;
import model.Computer;
import model.ComputerFactory;

public class EntityMapper {
	
	public static List<Computer> mapComputerList(ResultSet results) throws SQLException{
		List<Computer> list = new ArrayList<>();
		while (results.next()) {
			list.add(new ComputerFactory().withId(results.getLong("id"))
					.withName(results.getString("name"))
					.withIntroduced(results.getTimestamp("introduced"))
					.withDiscontinued(results.getTimestamp("discontinued"))
					.withCompanyId(results.getLong("company_id"))
					.build()
					);
		}
		return list;
	}
	
	public static Computer mapSingleComputer(ResultSet results) throws SQLException{
		if (results.next()) {
			return new ComputerFactory().withId(results.getLong("id"))
					.withName(results.getString("name"))
					.withIntroduced(results.getTimestamp("introduced"))
					.withDiscontinued(results.getTimestamp("discontinued"))
					.withCompanyId(results.getLong("company_id"))
					.build();
		}
		return null;
	}
	
	public static List<Company> mapCompaniesList(ResultSet results) throws SQLException{
		List<Company> list = new ArrayList<>();
		while (results.next()) {
			list.add(new CompanyFactory()
					.withId(results.getLong("id"))
					.withName(results.getString("name"))
					.build()
					);
		}
		return list;
	}
	
	public static Company mapSingleCompany(ResultSet results) throws SQLException{
		if (results.next()) {
			return new CompanyFactory()
					.withId(results.getLong("id"))
					.withName(results.getString("name"))
					.build();
		}		
		return null;
	}

}
