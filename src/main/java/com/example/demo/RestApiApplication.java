package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.controller.model.Employee;
import com.example.demo.controller.repository.EmployeeRepository;

@SpringBootApplication
public class RestApiApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		Employee employee = new Employee();
//		employee.setEmail("akash@soft.com");
//		employee.setPassword("akash111");
//		employee.setFirstName("Akash");
//		employee.setLastName("Sahani");
//		employeeRepository.save(employee);
//		
//		Employee employee1 = new Employee();
//		employee1.setEmail("bibek@soft.com");
//		employee1.setPassword("akash111");
//		employee1.setFirstName("Bibek");
//		employee1.setLastName("Sahani");
//		employeeRepository.save(employee1);
//		
	}

}
