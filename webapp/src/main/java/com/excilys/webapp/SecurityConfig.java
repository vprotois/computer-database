package com.excilys.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	  private static final String[] AUTH_WHITELIST = {
			  "/login",
			  "/logout"
			  
	  };
	
	 protected void configure(final HttpSecurity http) throws Exception {
	        http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers(AUTH_WHITELIST).permitAll() 
	        .anyRequest().authenticated()
	        .and()
	        .formLogin()  
	        .defaultSuccessUrl("/", true)
	        .and()
	        .logout()
	        .logoutUrl("/logout")
	        .deleteCookies("JSESSIONID");
	    }
	
}
