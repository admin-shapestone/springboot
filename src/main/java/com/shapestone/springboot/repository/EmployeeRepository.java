package com.shapestone.springboot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shapestone.springboot.model.Employee;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Integer>{
	Employee findByName(String name);
}
