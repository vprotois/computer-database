package app;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

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
	
	 @Bean
	  public LocalSessionFactoryBean sessionFactory() {
	      LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	      sessionFactory.setDataSource(DataSource());
	      sessionFactory.setPackagesToScan("model");
	      sessionFactory.setHibernateProperties(hibernateProperties());
	      return sessionFactory;
	  }
	 
	 @Bean
	  public PlatformTransactionManager hibernateTransactionManager() {
	      HibernateTransactionManager transactionManager = new HibernateTransactionManager();
	      transactionManager.setSessionFactory(sessionFactory().getObject());
	      return transactionManager;
	  }
	
	 
	 private Properties hibernateProperties() {
	      Properties hibernateProperties = new Properties();
	      hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	      return hibernateProperties;
	  }
	
}
