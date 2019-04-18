package app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = {"persistance","services","controler"})
@PropertySource("classpath:/datasource.properties")
public class AppConfig {

	@Autowired
    Environment env;
	
	
	@Bean
	public DataSource DataSource() {
		 DriverManagerDataSource dataSource = new DriverManagerDataSource();
		 dataSource.setUrl(env.getProperty("jdbcUrl"));
		 dataSource.setDriverClassName(env.getProperty("driverClassName"));
		 dataSource.setUsername(env.getProperty("db.username"));
		 dataSource.setPassword(env.getProperty("db.password"));
		 return dataSource;
		 
	}

	@Bean
	public JdbcTemplate JdbcTemplate() {
		return new JdbcTemplate(DataSource());
		
	}
	
	
}
