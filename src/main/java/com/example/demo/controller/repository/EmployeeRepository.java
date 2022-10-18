package com.example.demo.controller.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.controller.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
//	 @Query("select e from emp e where e.email like %?1")
//	  List<Employee>logInWithEmail(String email);
	
	
	 
	 @Query(value = "SELECT * FROM emp WHERE email = ?1", nativeQuery = true)
	  Map<String, String> logInWithEmail(String email);
	  
	 
	 @Query(value = "SELECT * FROM emp WHERE password = ?1", nativeQuery = true)
	  Employee logInWithPassword(String password);
	

}
