package com.excilys.webapp;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	 protected void configure(final HttpSecurity http) throws Exception {
	        http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/add","/edit","/delete").hasAuthority("MANAGER")
	        .antMatchers("/dashboard").authenticated()
	        .antMatchers("/login").permitAll()
	        .and()
	        .formLogin().loginPage("/login")
	        .loginProcessingUrl("/logged")
	        .usernameParameter("login")	
	        .passwordParameter("password")
	        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	    }
	 
}
