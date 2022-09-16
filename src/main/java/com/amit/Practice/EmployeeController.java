package com.amit.Practice;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.expression.spel.ast.OpAnd;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.Delegate;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeBuilder employeeBuilder;

	@GetMapping("/test")
	public String test() {
		return "running...";
	}

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {

		return employeeBuilder.getAllEmployees();
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") int id)
			throws EmployeeNotFoundException {

		return ResponseEntity.status(HttpStatus.OK).body(employeeBuilder.getEmployeeById(id));

	}

	@PostMapping("employee/create")
	public ResponseEntity<Map<String, Employee>> createEmployee(@RequestBody Employee employee)
			throws EmployeeValidationException {

		return ResponseEntity.status(HttpStatus.CREATED).body(employeeBuilder.createEmployee(employee));

	}

	@DeleteMapping("employee/delete/{id}")
	public ResponseEntity<Map<String, Employee>> deleteEmployee(@PathVariable(value = "id") int id)
			throws EmployeeNotFoundException {

		return ResponseEntity.status(HttpStatus.OK).body(employeeBuilder.deleteEmployeeById(id));

	}

	@PutMapping("employee/update/{id}")
	public ResponseEntity<Map<String, Employee>> updateEmployee(@RequestBody Employee employee, @PathVariable int id)
			throws EmployeeNotFoundException, EmployeeValidationException {

		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeBuilder.updateEmployee(employee, id));

	}

	// Get employee by user name
	@GetMapping("/employee/name/{userName}")
	public Optional<Employee> getByUserName(@PathVariable(value = "userName") String userName) {
		return this.employeeBuilder.findEmployeeByEmployeeUserName(userName);
	}

}
