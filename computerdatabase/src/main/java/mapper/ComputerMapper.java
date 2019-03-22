package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.Computer;
import model.builders.ComputerBuilder;

public class ComputerMapper {
	
	public static List<Computer> mapComputerList(ResultSet results) throws SQLException{
		List<Computer> list = new ArrayList<>();
		while (results.next()) {
			list.add(buildFromResult(results));
		}
		return list;
	}
	
	public static Computer mapSingleComputer(ResultSet results) throws SQLException{
		if (results.next()) {
			return buildFromResult(results);
		}
		return null;
	}
	
	private static Computer buildFromResult(ResultSet results) throws SQLException {
		return new ComputerBuilder().withId(results.getLong("id"))
				.withName(results.getString("name"))
				.withIntroduced(results.getTimestamp("introduced"))
				.withDiscontinued(results.getTimestamp("discontinued"))
				.withCompanyId(results.getLong("company_id"))
				.withCompany(new Company(
						results.getLong("cr.company_id"),
						results.getString("cy.name")))
				.build();
	}

}
