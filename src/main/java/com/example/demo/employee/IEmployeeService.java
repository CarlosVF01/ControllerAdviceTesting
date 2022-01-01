package com.example.demo.employee;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IEmployeeService {

	EmployeeDto addEmployee(EmployeeDto employee);

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmployeeById(Long empidL);

	ResponseEntity<String> deleteEmployeeById(Long empidL);

	List<EmployeeDto> getEmployeesWithAgeGreaterThan(int age);

	ResponseEntity<String> updateEmployee(Long empidL, EmployeeDto employeeDto);

}
