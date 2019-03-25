package persistance;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;

public class DataTest {
	
	private static String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db";
	private static String login = "admincdb";
	private static String password = "qwerty1234";
	private static String path_data = "/home/excilys/eclipse-workspace/computer-database/computerdatabase/src/test/java/persistance/test_data.xml";
	
	public static void main(String...strings) throws ClassNotFoundException, SQLException, DatabaseUnitException, FileNotFoundException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
	    Connection jdbcConnection = DriverManager.getConnection(url,login,password);

	    IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);


	    QueryDataSet dataSet = new QueryDataSet(connection);
	    dataSet.addTable("computer", "SELECT * FROM computer");
	    dataSet.addTable("company", "SELECT * FROM company");
	    
	    FlatXmlDataSet.write(dataSet, new FileOutputStream(path_data));
	    jdbcConnection.close();
	}
	
}
