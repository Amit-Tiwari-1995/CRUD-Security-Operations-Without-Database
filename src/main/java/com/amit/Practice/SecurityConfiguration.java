package com.amit.Practice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	
	// Security using UserDetailsService
	@Autowired
	private UserDetailsService userDetailsService;
	

	public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		  auth.inMemoryAuthentication().withUser("amit").password("amit").roles("USER")
//		  .and().withUser("ajeesh").password("ajeesh").roles("ADMIN");

		auth.userDetailsService(userDetailsService);
	}

	public void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().antMatchers(HttpMethod.GET, "/employees").hasAnyRole("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/employee/delete/{id}").hasAnyRole("USER").and().formLogin().and()
				.csrf().disable();

		// httpbasic() is used if you are using postman vis basic auth

		// .csrf().disable() is used for DELETE,POST,PUT operation
		// we need to disable csrf protection as bydefault it is enable
		// in spring security for DELETE,POST,PUT operation.

	}

	@Bean
	public PasswordEncoder passwordIncoderInstance() {
		return NoOpPasswordEncoder.getInstance();
	}

}
