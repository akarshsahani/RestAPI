package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.example.demo.controller.exception.ResourceNotFoundException;
import com.example.demo.controller.model.Employee;
import com.example.demo.controller.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@GetMapping
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	
	//built create employee REST API
	@PostMapping
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	//get employee by ID
	@GetMapping("{id}")
	public ResponseEntity<Employee> getEmployeeId(@PathVariable long id){
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee not exist with id: " + id));
		return ResponseEntity.ok(employee);
	}
	
	//update employee
	@PutMapping("{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id , @RequestBody Employee employeeDetails){
		Employee updateEmployee = employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee bot exist with id: " + id));
		
		if(employeeDetails.getEmail() != null) {
			updateEmployee.setEmail(employeeDetails.getEmail());
		}
		
		if(employeeDetails.getPassword() != null) {
			updateEmployee.setPassword(employeeDetails.getPassword());
		}
		
		if(employeeDetails.getFirstName() != null) {
			updateEmployee.setFirstName(employeeDetails.getFirstName());
		}
		
		if(employeeDetails.getLastName() != null) {
			updateEmployee.setLastName(employeeDetails.getLastName());
		}
		
		
		employeeRepository.save(updateEmployee);
		return ResponseEntity.ok(updateEmployee);
	}
	
	//delete employee.
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable(name = "id") long userId){
		Employee employee = employeeRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("Employee not found with id: " + userId));
		employeeRepository.delete(employee);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}
	
	//login with email
	@PostMapping("/login")
	public ResponseEntity logInEmployee (@RequestBody Map<String, String> email) {
		String emails = email.get("email");
		String passwords = email.get("password");
		System.out.println("tworking : "+ emails);
		System.out.println("tworking : "+ passwords);
		Map <String, String> a =  employeeRepository.logInWithEmail(emails);
		//Map<String, String>a;
		
		System.out.println(a);
		System.out.println((String)a.get("password"));
		
		
		Map response = new HashMap<>();
		if(a == null) {
			response.put("error", "User not found");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		String password = (String)a.get("password");
		if (password.equals(passwords)) {
			response.put("message", "Login sucess");
			 response.put("userDetails", a);
			 return new ResponseEntity<>(response, HttpStatus.OK);}
			else {
				response.put("error", "Wrong password");
				 return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
//	
//	// login for password;
//	@PostMapping("/password")
//	public Employee logInEmployeePass (@RequestBody String password) {
//		EmployeeController a = new EmployeeController();
//		int aa = a.logInEmployee("akash@soft.com");
//		//System.out.println(aa);
//		
//		if(aa == 1) 
//			employeeRepository.logInWithPassword(password);
//			
//		}
//		else {
//			System.out.println("Invalid Email");
//		}
//		return null;
//	}
	
	
//	public int logInEmployeeEmail (String email) {
//		System.out.println("5");
//		int check = 0;
//		employeeRepository.logInWithEmail(email);
//		check = 1;
//		System.out.println("6");
//		//System.out.println(check);
//		return check;
//	}
//	
//	public int logInEmployeePass (String email, String password) {
//		System.out.println("3");
//		EmployeeController a = new EmployeeController();
//		int aa = a.logInEmployeeEmail(email);
//		System.out.println("4");
//		int check =0;
//		
//		if(aa == 1) {
//			employeeRepository.logInWithPassword(password);
//			check =1;
//			System.out.println("4.1");
//			
//		}
//		else {
//			System.out.println("Invalid Email");
//			System.out.println("4.2");
//		}
//		return check;
//	}
//	
//	
//	@PostMapping("/auth")
//	public Employee auth(@RequestBody Employee loginDetails) {
//		int checkPass = 0;
//		System.out.println("1");
//		String email = loginDetails.getEmail();
//		System.out.println("2");
//		System.out.println(email);
//		String password = loginDetails.getPassword();
//		System.out.println(password);
//		
//		EmployeeController a = new EmployeeController();
//		checkPass = a.logInEmployeePass(email, password);
//		
//		if(checkPass == 1) {
//			System.out.println("login successful");
//		}
//		else {
//			System.out.println("Invalid Password");
//		}
//		
//		return null;
//	}

