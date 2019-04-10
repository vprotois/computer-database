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
		return new HikariConfig("/home/excilys/eclipse-workspace/computer-database/computerdatabase/src/main/ressources/datasource.properties");
	}

	@Bean
	public HikariDataSource HikariDataSource() {
		return new HikariDataSource(HikariConfig());
	}

	@Bean
	public DAOComputer DAOComputer() {
		return new DAOComputer(HikariDataSource());
	}

	@Bean
	public DAOCompany DAOCompany() {
		return new DAOCompany(HikariDataSource());
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
