package com.amit.Practice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionValidation {

	
	@ExceptionHandler
	public Map<String, String> employeeNotFoundExceptionValidation(EmployeeNotFoundException ex) {
		Map<String, String> Errormap = new HashMap<>();
		Errormap.put("errorMessage", ex.getMessage());

		return Errormap;
	}

}
