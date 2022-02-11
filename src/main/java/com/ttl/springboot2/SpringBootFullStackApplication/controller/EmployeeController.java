package com.ttl.springboot2.SpringBootFullStackApplication.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ttl.springboot2.SpringBootFullStackApplication.model.Employee;
import com.ttl.springboot2.SpringBootFullStackApplication.service.MyService;




@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = {"GET","POST","PUT","DELETE"})
//@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	MyService service;
	
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**").allowedOrigins("*");
	            }
	        };
	    }

// localhost:8080/api/v1/employee/all

	@RequestMapping(value = "/employee/all", method = RequestMethod.GET)
	public List<Employee> getEmployees() {
		System.out.println(this.getClass().getSimpleName() + " getEmployees() method invoked");

		return service.getEmployees();
	}

// localhost:8080/projectname/employee/1234 ==> 1234 @PathVariable id
	@RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
	public Employee getEmployeeById(@PathVariable int id) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " getEmployeeById() method invoked");
		Optional<Employee> emp = service.getEmployeeById(id);

		if (!emp.isPresent())
			throw new Exception("could not find Employee with id " + id);

		return emp.get();
	}

	@RequestMapping(value = "/employee/add", method = RequestMethod.POST)
	public Employee createEmployee(@RequestBody Employee emp) {
		System.out.println(this.getClass().getSimpleName() + " createEmployee method invoked");
		return service.addNewEmployee(emp);
	}

	@RequestMapping(value = "/employee/update/{id}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee UpEmp) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " createEmployee method invoked");

		Optional<Employee> emp = service.getEmployeeById(id);

		if (!emp.isPresent())
			throw new Exception("could not find Employee with id " + id);

		if (UpEmp.getName() == null || UpEmp.getName().isEmpty())
			UpEmp.setName(emp.get().getName());
		if (UpEmp.getDepartment() == null || UpEmp.getDepartment().isEmpty())
			UpEmp.setDepartment(emp.get().getDepartment());
		if (UpEmp.getSalary() == 0.0)
			UpEmp.setSalary(emp.get().getSalary());

// for where clause
		UpEmp.setId(id);

		return service.updateEmployee(UpEmp);

	}

	@RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.DELETE)
	public void deleteEmployeeById(@PathVariable int empid) throws Exception {
		System.out.println(this.getClass().getSimpleName() + " deleteEmployeeById() method invoked");
		Optional<Employee> emp = service.getEmployeeById(empid);

		if (!emp.isPresent())
			throw new Exception("could not find Employee with id " + empid);

		service.deleteEmployeeById(empid);
	}

	@RequestMapping(value = "/employee/deleteall", method = RequestMethod.DELETE)
	public void deleteAll() {
		System.out.println(this.getClass().getSimpleName() + " deleteAll() method invoked");

		service.deleteAllEmployees();
	}

}

