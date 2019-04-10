package app;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
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
		 DataSource dataSource = DataSourceBuilder
				 .create()
				 .url(env.getProperty("jdbcUrl"))
				 .driverClassName(env.getProperty("driverClassName"))
				 .username(env.getProperty("db.username"))
				 .password(env.getProperty("db.password"))
				 .build();
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
