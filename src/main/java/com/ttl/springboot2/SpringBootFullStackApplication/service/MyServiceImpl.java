package com.ttl.springboot2.SpringBootFullStackApplication.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttl.springboot2.SpringBootFullStackApplication.model.Employee;
import com.ttl.springboot2.SpringBootFullStackApplication.repository.EmployeeRepository;



@Service
public class MyServiceImpl implements MyService {

	@Autowired
	EmployeeRepository dao;

	@Override
	public List<Employee> getEmployees() {
// TODO Auto-generated method stub
		return dao.findAll(); // select all
	}

	@Override
	public Optional<Employee> getEmployeeById(int empid) {
// TODO Auto-generated method stub
		return dao.findById(empid); // select one record based on id
	}

	@Override
	public Employee addNewEmployee(Employee emp) {
// TODO Auto-generated method stub
		return dao.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) {
// TODO Auto-generated method stub
		return dao.save(emp);
	}

	@Override
	public void deleteEmployeeById(int empid) {
// TODO Auto-generated method stub
		dao.deleteById(empid);
	}

	@Override
	public void deleteAllEmployees() {
		dao.deleteAll();

	}



}
