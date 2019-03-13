package model;
import java.sql.*;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;



public class ComputerDB {
	private Connection conn;
	private Hashtable<Integer,Company> companies;

	public ComputerDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
		try {
			conn  = DriverManager.getConnection(url,"admincdb","qwerty1234");
			companies = getCompanies();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	protected void finalize()
	{
		try {
			if (conn != null && !conn.isClosed())
				conn.close();
		} catch (Exception e) {}
	}


	public Company getCompany(Integer id) {
		return this.companies.get(id);
	}
	
	public ResultSet listComputers() throws SQLException {
		String query = "SELECT id,name,introduced,discontinued,company_id FROM computer;";
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(query);
	}

	public Hashtable<Integer,Company> getCompanies() throws SQLException {
		if (this.companies == null) {
			String query = "SELECT id,name FROM company;";
			Statement stmt = conn.createStatement();
			Hashtable<Integer,Company> companies = new Hashtable<Integer,Company>();
			ResultSet results =  stmt.executeQuery(query);
			while (results.next()) {
				int id = results.getInt("id");
				companies.put(id,new Company(id,results.getString("name")));
			}
			return companies;			
		}
		return this.companies;
	}
	
	public ResultSet getCompDetails(Long id) throws SQLException {
		String query = "SELECT * FROM computer WHERE id ="+id.longValue()+";";
		Statement stmt = conn.createStatement();
		return stmt.executeQuery(query);
	}
	
	public void createComputer(Computer c) throws SQLException {
		
		PreparedStatement prep = conn.prepareStatement(
				"INSERT INTO computer "
				+ "(id, name, introduced, discontinued,company_id) VALUES "
				+ "(?,?,?,?,?);");
		prep.setLong(1,c.getId());
		prep.setString(2,c.getName());
		prep.setTimestamp(3,c.getIntroduced());
		prep.setTimestamp(4,c.getDiscontinued());
		prep.setLong(5,c.getCompany_id());
		prep.executeUpdate();
	}
	
	public void updateComputer(Long id,String colonne, String value) throws SQLException {
		String query = "SELECT * FROM computer WHERE id ="+id.longValue()+";";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		if (results.next()) {
			PreparedStatement prep1 = conn.prepareStatement("UPDATE computer SET ?=? WHERE id = ?");
			prep1.setString(1,colonne);
			prep1.setString(2,value);
			prep1.setLong(3,id);
			prep1.executeUpdate();
		}
	}
	
	public void deleteComputer(Long id) throws SQLException {
		String query = "DELETE FROM computer WHERE id ="+id.longValue()+";";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

}
