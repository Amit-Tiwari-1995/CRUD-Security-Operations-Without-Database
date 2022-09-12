package com.amit.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class EmployeeBuilder {

	private List<Employee> listEmployees = new ArrayList<Employee>(
			Arrays.asList(new Employee(101, "ajeesh", "Ajeesh Nair", 89000.00, "ajeesh", "ROLE_ADMIN"),
					new Employee(102, "amit", "Amit Tiwari", 74000.00, "amit", "ROLE_USER")));

	// list of employees

	public List<Employee> getAllEmployees() {

		Collections.sort(listEmployees, (a, b) -> a.getId() - b.getId());
		return listEmployees;

	}

	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
		Employee employee = listEmployees.stream().filter(l -> l.getId() == id).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

		return employee;

	}

	public Map<String, Employee> createEmployee(Employee employee) throws EmployeeValidationException {
		Employee newEmployee = new Employee();

		for (int i = 0; i < listEmployees.size(); i++) {
			if (employee.getId() == listEmployees.get(i).getId()) {
				throw new EmployeeValidationException("Employee id is duplicate with: " + listEmployees.get(i));
			}
		}

		newEmployee.setId(employee.getId());

		if (employee.getName() == null || employee.getName().isEmpty()) {
			throw new EmployeeValidationException("Name can't be null or blank value");
		}

		String regex = "(?=^.{0,40}$)^[a-zA-Z-]+\\s[a-zA-Z-]+$";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(employee.getName());

		if (!m.matches()) {
			throw new EmployeeValidationException(
					"Employee name should starts with alphabet, should not have special character and please add surname also");
		}

		newEmployee.setName(employee.getName());

		if (!(employee.getSalary() >= 30000 && employee.getSalary() <= 100000)) {
			throw new EmployeeValidationException(
					"Employee salary should be greater than 30K and less than 1L, your salary: "
							+ employee.getSalary());

		}

		newEmployee.setSalary(employee.getSalary());
		
		newEmployee.setEmployeeUserName(employee.getEmployeeUserName());
		newEmployee.setEmployeePassword(employee.getEmployeePassword());
		newEmployee.setEmployeeRole(employee.getEmployeeRole());

		listEmployees.add(newEmployee);

		Map<String, Employee> mapEmployee = new HashMap<String, Employee>();
		mapEmployee.put("Employee created: ", newEmployee);

		return mapEmployee;

	}

	public Map<String, Employee> deleteEmployeeById(int id) throws EmployeeNotFoundException {

		Employee employee = null;
		for (int i = 0; i < listEmployees.size(); i++) {

			if (listEmployees.get(i).getId() == id) {
				employee = listEmployees.get(i);
				listEmployees.remove(i);
			}
		}

		if (employee == null) {
			throw new EmployeeNotFoundException("Employee not found with id: " + id);
		}

		Map<String, Employee> mapEmployee = new HashMap<String, Employee>();
		mapEmployee.put("Employee deleted: ", employee);

		return mapEmployee;

	}

	public Map<String, Employee> updateEmployee(Employee employee, int id)
			throws EmployeeNotFoundException, EmployeeValidationException {

		Employee UpdatedEmployee = listEmployees.stream().filter(l -> l.getId() == id).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

		String regex = "(?=^.{0,40}$)^[a-zA-Z-]+\\s[a-zA-Z-]+$";

		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(employee.getName());

		if (!m.matches()) {
			throw new EmployeeValidationException(
					"Employee name should starts with alphabet, should not have special character and please add surname also");
		}

		UpdatedEmployee.setName(employee.getName());

		if (!(employee.getSalary() >= 30000 && employee.getSalary() <= 100000)) {
			throw new EmployeeValidationException(
					"Employee salary should be greater than 30K and less than 1L, your salary: "
							+ employee.getSalary());

		}

		UpdatedEmployee.setSalary(employee.getSalary());
		
		UpdatedEmployee.setEmployeeUserName(employee.getEmployeeUserName());
		UpdatedEmployee.setEmployeePassword(employee.getEmployeePassword());
		UpdatedEmployee.setEmployeeRole(employee.getEmployeeRole());

		for (int i = 0; i < listEmployees.size(); i++) {
			if (listEmployees.get(i).getId() == id) {
				listEmployees.set(i, UpdatedEmployee);
			}

		}

		Map<String, Employee> mapEmployee = new HashMap<String, Employee>();
		mapEmployee.put("Employee updated: ", UpdatedEmployee);

		return mapEmployee;

	}

	// find by Employee user_name
	public Optional<Employee> findEmployeeByEmployeeUserName(String userName) {

		Optional<Employee> employee = listEmployees.stream().filter(l -> l.getEmployeeUserName().equals(userName))
				.findFirst();

		return employee;

	}

}
