package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Company;
import model.CompanyBuilder;

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

	public static Company mapSingleCompany(ResultSet results) throws SQLException{
		if (results.next()) {
			return new CompanyBuilder()
					.withId(results.getLong("id"))
					.withName(results.getString("name"))
					.build();
		}		
		return null;
	}

}
