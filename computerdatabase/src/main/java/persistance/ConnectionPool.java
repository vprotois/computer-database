package persistance;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

	private static final String PROPERTIES_PATH = "/home/excilys/eclipse-workspace/computer-database/computerdatabase/src/main/ressources/datasource.properties";
	
	private static HikariConfig config = new HikariConfig(PROPERTIES_PATH);



	private ConnectionPool() {

	}

	private enum Pool{
		INSTANCE(new HikariDataSource(config))
		;

		private final HikariDataSource dataSource;
		private Pool(HikariDataSource dataSource) {
			this.dataSource = dataSource;
		}
		public HikariDataSource getDataSource() {
			return dataSource;
		}
	}

	public static HikariDataSource getDataSource() {
		return Pool.INSTANCE.getDataSource();
	}


}
