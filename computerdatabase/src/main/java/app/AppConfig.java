package app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import com.zaxxer.hikari.HikariConfig;
//import com.zaxxer.hikari.HikariDataSource;

import persistance.DAOComputer;
import persistance.DAOCompany;

import services.CompanyServices;
import services.ComputerServices;

import controler.CLIControler;

@Configuration
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
	public DAOComputer DAOComputer() {
		return new DAOComputer(DataSource());
	}

	@Bean
	public DAOCompany DAOCompany() {
		return new DAOCompany(DataSource());
	}
	
	@Bean
	public CompanyServices CompanyServices() {
		return new CompanyServices(DAOCompany());
	}

	@Bean
	public ComputerServices computerServices() {
		return new ComputerServices(DAOComputer());
	}
	
	@Bean
	public CLIControler CLIControler() {
		return new CLIControler(computerServices(),CompanyServices());
	}
}
