package com.example.demo;

import com.example.demo.aws.s3test.S3Test;
import com.example.demo.employee.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ControllerAdviceTestingApplication {

	@Autowired
	private EmployeeRepository employeeRepository;

	public static void main(String[] args) {
		SpringApplication.run(ControllerAdviceTestingApplication.class, args);
		S3Test.init();
	}
}
