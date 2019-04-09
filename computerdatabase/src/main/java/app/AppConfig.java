package app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import persistance.DAOComputer;
import persistance.DAOCompany;

import services.CompanyServices;
import services.ComputerServices;

import controler.CLIControler;

@Configuration
public class AppConfig {

	@Bean
	public HikariConfig HikariConfig() {
		return new HikariConfig("/datasource.properties");
	}

	@Bean
	public HikariDataSource HikariDataSource() {
		return new HikariDataSource(HikariConfig());
	}

	@Bean
	public DAOComputer DAOComputer() {
		return new DAOComputer();
	}

	@Bean
	public DAOCompany DAOCompany() {
		return new DAOCompany();
	}
	
	@Bean
	public CompanyServices CompanyServices() {
		return new CompanyServices();
	}

	@Bean
	public ComputerServices computerServices() {
		return new ComputerServices();
	}
	
	@Bean
	public CLIControler CLIControler() {
		return new CLIControler();
	}
}
