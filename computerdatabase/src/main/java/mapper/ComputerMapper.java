package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	
	public static Optional<Computer> mapSingleComputer(ResultSet results) throws SQLException{
		if (results.next()) {
			return Optional.of(buildFromResult(results));
		}
		return Optional.empty();
	}
	
	private static Computer buildFromResult(ResultSet results) throws SQLException {
		return new ComputerBuilder().withId(results.getLong("cr.id"))
				.withName(results.getString("cr.name"))
				.withIntroduced(results.getTimestamp("cr.introduced"))
				.withDiscontinued(results.getTimestamp("cr.discontinued"))
				.withCompanyId(results.getLong("cr.company_id"))
				.withCompany(new Company(
						results.getLong("cr.company_id"),
						results.getString("cy.name")))
				.build();
	}

}
