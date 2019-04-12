package app;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = {"persistance","services","controler"})
@PropertySource("classpath:/datasource.properties")
public class AppConfig {

	@Autowired
    Environment env;
	
	
	@Bean(destroyMethod="close")
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

}
