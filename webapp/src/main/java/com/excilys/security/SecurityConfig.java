package com.excilys.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.DigestAuthenticationEntryPoint;
import org.springframework.security.web.authentication.www.DigestAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Override
	 protected void configure(final HttpSecurity http) throws Exception {
	        http
	        .csrf().disable()
	        .authorizeRequests()
	        .antMatchers("/add","/edit","/delete").hasAuthority("MANAGER")
	        .antMatchers("/dashboard").authenticated()
	        .antMatchers("/login").permitAll()
	        .and()
	        .formLogin().loginPage("/login")
	        .loginProcessingUrl("/authenticate")
	        .usernameParameter("username")	
	        .passwordParameter("password")
	        .successHandler(authenticationHandler())
            .failureHandler(authenticationHandler())
	        .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
	    }
	 
	 @Bean
	 public AuthenticationHandler authenticationHandler() {
	        return new AuthenticationHandler();
	 }
	 
	 @Bean
	    public DigestAuthenticationFilter digestAuthenticationFilter(UserService userDetailsService) {
	        DigestAuthenticationFilter authFilter = new DigestAuthenticationFilter();
	        authFilter.setAuthenticationEntryPoint(digestAuthenticationEntryPoint());
	        authFilter.setUserDetailsService(userDetailsService);
	        return authFilter;
	    }

	    @Bean
	    public DigestAuthenticationEntryPoint digestAuthenticationEntryPoint() {
	        DigestAuthenticationEntryPoint entryPoint = new DigestAuthenticationEntryPoint();
	        entryPoint.setNonceValiditySeconds(600);
	        entryPoint.setKey("This is my key for digest authentication.");
	        entryPoint.setRealmName("This is the realm name");
	        return entryPoint;
	    }
	 
	 @Bean
	 public UserService userDetailsService() {
	        return new UserService();
	 }
	 
	 @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
}
