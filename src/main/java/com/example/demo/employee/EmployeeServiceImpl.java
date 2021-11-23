package com.example.demo.employee;

import java.util.List;

import com.example.demo.exception.ElementNotFoundException;
import com.example.demo.exception.EmptyBodyException;
import com.example.demo.utils.ObjectMapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeServiceImpl implements IEmployeeService {
	
	@Autowired
	private EmployeeRepository crudRepo;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employeeDto) {
		if(employeeDto.getName().isEmpty()){
			throw new EmptyBodyException("Employee name cannot be empty");
		}
		Employee employee = ObjectMapperUtils.map(employeeDto, Employee.class);
		crudRepo.save(employee);
		return employeeDto;
		
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {

		List<Employee> employees = crudRepo.findAll();

		return ObjectMapperUtils.mapAll(employees, EmployeeDto.class);
	}

	@Override
	public EmployeeDto getEmpById(Long empidL) {
		Employee employee = crudRepo.findById(empidL).get();

		return ObjectMapperUtils.map(employee, EmployeeDto.class);
	}

	@Override
	public ResponseEntity<String> deleteEmpById(Long empidL) {
		if (crudRepo.findById(empidL).isPresent()) {
			crudRepo.deleteById(empidL);
			log.info("Employee with ID {} succesfully deleted", empidL);
			return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
		}
			throw new ElementNotFoundException("An employee with the ID: " + empidL + " doesn't exist");

	}

}
