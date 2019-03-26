package persistance;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

	private static String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	private static String login = "admincdb";
	private static String password = "qwerty1234";
	
	private static DAOCompany daoCompany;
	private static DAOComputer daoComputer;
	
	public DAOFactory() {
		
	}
	
	
	public static DAOCompany createDAOcompany(){
		if(daoCompany == null) {
			daoCompany = new DAOCompany();
		}
		return daoCompany;
	}
	
	public static DAOComputer createDAOcomputer(){
		if(daoComputer == null) {
			daoComputer = new DAOComputer();
		}
		return daoComputer;
	}
	
	static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		conn  = DriverManager.getConnection(url,login,password);
		return conn;
	}
	
}