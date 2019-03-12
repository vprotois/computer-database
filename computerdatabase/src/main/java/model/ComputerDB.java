package model;
import java.sql.*;



public class ComputerDB {
	private Connection conn;

	public ComputerDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
		try {
			conn  = DriverManager.getConnection(url,"admincdb","qwerty1234");
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


	public void listComputers() throws SQLException {

		String query = "SELECT * FROM computer;";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		while (results.next()) {

			System.out.println(results.getInt("id")+" "
					+ results.getString("name") + " "
					+ results.getTimestamp("introduced") + " "
					+ results.getTimestamp("discontinued") + " "
					+ results.getInt("company_id")
					);
		}
	}

	public void listCompanies() throws SQLException {

		String query = "SELECT * FROM company;";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		while (results.next()) {

			System.out.println(results.getInt("id")+" "
					+ results.getString("name")
					);
		}
	}
	
	public void showCompDetails(int id) throws SQLException {
		String query = "SELECT * FROM computer WHERE id ="+id+";";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		while (results.next()) {
			System.out.println(results.getInt("id")+" "
					+ results.getString("name") + " "
					+ results.getTimestamp("introduced") + " "
					+ results.getTimestamp("discontinued") + " "
					+ results.getInt("company_id")
					);
		}
	}
	
	public void createComputer(Computer c) throws SQLException {
		
		PreparedStatement prep = conn.prepareStatement(
				"INSERT INTO computer "
				+ "(id, name, introduced, discontinued,company_id) VALUES "
				+ "(?,?,?,?,?);");
		prep.setInt(1,c.getId());
		prep.setString(2,c.getName());
		prep.setTimestamp(3,c.getIntroduced());
		prep.setTimestamp(4,c.getDiscontinued());
		prep.setString(5,c.getName());
		prep.executeUpdate();
	}
	
	public void updateComputer(int id,String colonne, String value) throws SQLException {
		String query = "SELECT * FROM computer WHERE id ="+id+";";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		if (results.next()) {
			PreparedStatement prep1 = conn.prepareStatement("UPDATE computer SET ?=? WHERE id = ?");
			prep1.setString(1,colonne);
			prep1.setString(2,value);
			prep1.setInt(3,id);
			prep1.executeUpdate();
		}
	}
	
	public void deleteComputer(int id) throws SQLException {
		String query = "DELETE FROM computer WHERE id ="+id+";";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}

}
