package persistance;



import org.junit.jupiter.api.Test;


import java.sql.SQLException;


class ConnectionPoolTest {

	@Test
	void test(){
		try {
			ConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
