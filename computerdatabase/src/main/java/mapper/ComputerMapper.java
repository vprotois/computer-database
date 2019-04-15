package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import model.Company;
import model.Computer;
import model.builders.ComputerBuilder;

public class ComputerMapper implements RowMapper<Computer>{
	
	Computer buildFromResult(ResultSet results) throws SQLException {
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

	@Override
	public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
		return buildFromResult(rs);
	}

}
