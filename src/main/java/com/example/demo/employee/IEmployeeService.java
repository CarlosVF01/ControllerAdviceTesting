package com.example.demo.employee;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface IEmployeeService {

	EmployeeDto addEmployee(EmployeeDto employee);

	List<EmployeeDto> getAllEmployees();

	EmployeeDto getEmpById(Long empidL);

	ResponseEntity<String> deleteEmpById(Long empidL);

}
