package com.amit.Practice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeExceptionValidation {

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> employeeNotFoundExceptionValidation(EmployeeNotFoundException ex) {
		Map<String, String> Errormap = new HashMap<>();
		Errormap.put("errorMessage", ex.getMessage());

		return Errormap;
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> employeeValidationExceptionValidation(EmployeeValidationException ex) {
		Map<String, String> Errormap = new HashMap<>();
		Errormap.put("errorMessage", ex.getMessage());

		return Errormap;
	}

}
