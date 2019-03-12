package model;
import java.sql.*;



public class ComputerDB {
	private Connection conn;

	public ComputerDB() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
		conn  = DriverManager.getConnection(url,"admincdb","qwerty1234");


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
		String query = "INSERT INTO computer VALUES (" + c.toString() +");";
		Statement stmt = conn.createStatement();
		stmt.executeUpdate(query);
	}
	
	public void updateComputer(int id,String colonne, String value) throws SQLException {
		String query = "SELECT * FROM computer WHERE id ="+id+";";
		Statement stmt = conn.createStatement();
		ResultSet results = stmt.executeQuery(query);
		while (results.next()) {
			PreparedStatement prep1 = conn.prepareStatement("UPDATE computer SET ?=? WHERE id = ?");
			prep1.setString(1,colonne);
			prep1.setString(2,value);
			prep1.setInt(3,id);
			prep1.executeUpdate();
		}
	}
	

}
