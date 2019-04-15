package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import model.Company;
import model.builders.CompanyBuilder;

public class CompanyMapper implements RowMapper<Company>{

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new CompanyBuilder()
				.withId(rs.getLong("id"))
				.withName(rs.getString("name"))
				.build();
	}

}
