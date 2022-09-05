package com.amit.Practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmployeeBuilder {

	private List<Employee> listEmployees = new ArrayList<Employee>(
			Arrays.asList(new Employee(2, "ajeesh", 89000.00), new Employee(1, "rajat", 74000.00)));

	public List<Employee> employeeBuilder() {

		return listEmployees;

	}

	// list of employees

	public List<Employee> getAllEmployees() {
		return listEmployees = employeeBuilder();
	}

	public Employee getEmployeeById(int id) throws EmployeeNotFoundException {
		Employee employee = employeeBuilder().stream().filter(l -> l.getId() == id).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

		return employee;

	}

	public Map<String, Employee> createEmployee(Employee employee) {
		Employee newEmployee = new Employee();
		newEmployee.setId(employee.getId());
		newEmployee.setName(employee.getName());
		newEmployee.setSalary(employee.getSalary());

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

	public Map<String, Employee> updateEmployee(Employee employee, int id) throws EmployeeNotFoundException {

		Employee UpdatedEmployee = employeeBuilder().stream().filter(l -> l.getId() == id).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id: " + id));

		UpdatedEmployee.setName(employee.getName());
		UpdatedEmployee.setSalary(employee.getSalary());

		for (int i = 0; i < listEmployees.size(); i++) {
			if (listEmployees.get(i).getId() == id) {
				listEmployees.set(i, UpdatedEmployee);
			}

		}

		Map<String, Employee> mapEmployee = new HashMap<String, Employee>();
		mapEmployee.put("Employee updated: ", UpdatedEmployee);

		return mapEmployee;

	}

}
