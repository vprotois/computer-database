package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import model.Company;
import model.builders.CompanyBuilder;

public class CompanyMapper {

	public static List<Company> mapCompaniesList(ResultSet results) throws SQLException{
		List<Company> list = new ArrayList<>();
		while (results.next()) {
			list.add(new CompanyBuilder()
					.withId(results.getLong("id"))
					.withName(results.getString("name"))
					.build()
					);
		}
		return list;
	}

	public static Optional<Company> mapSingleCompany(ResultSet results) throws SQLException{
		if (results.next()) {
			return Optional.of(new CompanyBuilder()
					.withId(results.getLong("id"))
					.withName(results.getString("name"))
					.build());
		}		
		return Optional.empty();
	}

}
