package com.amit.Practice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeBuilder employeeBuilder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Employee> employee = this.employeeBuilder.findEmployeeByEmployeeUserName(username);

		employee.orElseThrow(() -> new UsernameNotFoundException("User not found with User name: " + username));

		MyUserDetails myUserDetails = employee.map(MyUserDetails::new).get();

		return myUserDetails;

	}

}
