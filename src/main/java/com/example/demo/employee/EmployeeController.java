package com.example.demo.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees/")
public class EmployeeController {

	@Autowired
	private IEmployeeService IEmployeeService;
	
	@PostMapping("")
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employee){
		EmployeeDto employeeSaved = IEmployeeService.addEmployee(employee);
		return new ResponseEntity<>(employeeSaved, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
		
		List<EmployeeDto> listOfAllEmps = IEmployeeService.getAllEmployees();
		return new ResponseEntity<>(listOfAllEmps, HttpStatus.OK);
	}
	
	@GetMapping("{empid}")
	public ResponseEntity<EmployeeDto> getEmpById(@PathVariable("empid") Long empidL){
		
		EmployeeDto empRetrieved = IEmployeeService.getEmpById(empidL);
		return new ResponseEntity<>(empRetrieved, HttpStatus.OK);
	}
	
	@DeleteMapping("{empid}")
	public ResponseEntity<String> deleteEmpById(@PathVariable("empid") Long empidL){
		return IEmployeeService.deleteEmpById(empidL);
	}
	
	@PutMapping("")
	public ResponseEntity<EmployeeDto> updateEmployee(@RequestBody EmployeeDto employee){
		EmployeeDto employeeSaved = IEmployeeService.addEmployee(employee);
		return new ResponseEntity<>(employeeSaved, HttpStatus.OK);
	}

	
	
}
