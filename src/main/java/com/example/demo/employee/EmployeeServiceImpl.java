package com.example.demo.employee;

import java.util.List;
import java.util.Optional;

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
	public EmployeeDto getEmployeeById(Long employeeId) {
		Optional<Employee> optionalEmployee = crudRepo.findById(employeeId);
		if(!optionalEmployee.isPresent()){
			throw new ElementNotFoundException(Employee.class, employeeId.toString());
		}
		Employee employee = optionalEmployee.get();

		return ObjectMapperUtils.map(employee, EmployeeDto.class);
	}

	@Override
	public ResponseEntity<String> deleteEmployeeById(Long employeeId) {
		if (crudRepo.findById(employeeId).isPresent()) {
			crudRepo.deleteById(employeeId);
			return new ResponseEntity<>("Employee deleted", HttpStatus.OK);
		}
			throw new ElementNotFoundException(Employee.class, employeeId.toString());

	}

	@Override
	public List<EmployeeDto> getEmployeesWithAgeGreaterThan(int age) {
		Optional<List<Employee>> optionalEmployeeList = crudRepo.findByAgeIsGreaterThan(age);
		if(!optionalEmployeeList.isPresent()){
			throw new ElementNotFoundException("Couldn't find any employees with an age greater or equal than: " + age);
		}
		return ObjectMapperUtils.mapAll(optionalEmployeeList.get(),EmployeeDto.class);
	}

	@Override
	public ResponseEntity<String> updateEmployee(Long empidL, EmployeeDto employeeDto) {
		Optional<Employee> employee = crudRepo.findById(empidL);

		if(!employee.isPresent()){
			throw new ElementNotFoundException(Employee.class, empidL.toString());
		}
		employee.get().setAge(employeeDto.getAge());
		employee.get().setName(employeeDto.getName());

		crudRepo.save(employee.get());
		return new ResponseEntity<>("Employee with ID: " + empidL + " has been updated", HttpStatus.OK);
	}

}
