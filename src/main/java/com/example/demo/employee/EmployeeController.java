package com.example.demo.employee;

import java.util.List;


import com.example.demo.aws.s3test.S3Test;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
@RequestMapping("/employees/")
public class EmployeeController {

	@Autowired
	private IEmployeeService employeeService;

	@PostMapping("aws/s3test")
	public static void s3Test(){
		S3Test.init();
	}

	@GetMapping("src/main/test")
	public ResponseEntity<EmployeeDto> test(){
		EmployeeDto employeeDto = new EmployeeDto("src/main/test", 1);

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(employeeDto);
	}
	
	@PostMapping("")
	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employee){
		EmployeeDto employeeSaved = employeeService.addEmployee(employee);

		return new ResponseEntity<>(employeeSaved, HttpStatus.CREATED);
	}
	
	@GetMapping("")
	public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
		List<EmployeeDto> listOfAllEmps = employeeService.getAllEmployees();
		return new ResponseEntity<>(listOfAllEmps, HttpStatus.OK);
	}

	@GetMapping("age/{empage}")
	public ResponseEntity<List<EmployeeDto>> getEmpByAgeGreaterThan(@PathVariable("empage") int empage){
		List<EmployeeDto> empRetrieved = employeeService.getEmployeesWithAgeGreaterThan(empage);
		return new ResponseEntity<>(empRetrieved, HttpStatus.OK);
	}
	
	@GetMapping("{empid}")
	public ResponseEntity<EmployeeDto> getEmpById(@PathVariable("empid") Long empidL){
		EmployeeDto empRetrieved = employeeService.getEmployeeById(empidL);
		return new ResponseEntity<>(empRetrieved, HttpStatus.OK);
	}
	
	@DeleteMapping("{empid}")
	public ResponseEntity<String> deleteEmpById(@PathVariable("empid") Long empidL){
		return employeeService.deleteEmployeeById(empidL);
	}
	
	@PutMapping("{empid}")
	public ResponseEntity<String> updateEmployee(@PathVariable Long empid, @RequestBody EmployeeDto employee){
		return employeeService.updateEmployee(empid, employee);
	}

	
	
}
