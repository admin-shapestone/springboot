package com.shapestone.springboot.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.shapestone.springboot.exception.EmployeePortalException;
import com.shapestone.springboot.model.Employee;
import com.shapestone.springboot.repository.EmployeeRepository;

import lombok.extern.slf4j.XSlf4j;

/**
 * This class is used to have a Employee services like CRUD operations
 * @author surya
 *
 */
@Service
@XSlf4j
public class EmployeeService {
	Log log = LogFactory.getLog(EmployeeService.class);
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * getEmpoyeeList method is used to get all employees.
	 * @return
	 */
	public List<Employee> getEmpoyeeList() {
		return (List<Employee>) employeeRepository.findAll();
	}
	/**
	 * validateAndSaveEmployee method for saving employee and performing employee validations
	 */
	public Employee validateAndSaveEmployee(Employee employee) {
		validateEmployee(employee);
		return employeeRepository.save(employee);
	}
	
	private void validateEmployee(Employee employee) {
		if (ObjectUtils.isEmpty(employee)) {
			log.error("Employee object is null..");
			throw new EmployeePortalException("Employee object is null, Please send valid data");
		}
		// TODO if there is any validation, those will go here
	}
	/**
	 * getEmpById method is used to get employee by Id
	 * @param empId
	 * @return
	 */
	public Employee getEmpById(int empId) {
		if (empId < 0) {
			throw new EmployeePortalException("Employee Id can't be non negative or zero");
		}
		Optional<Employee> findById = employeeRepository.findById(empId);
		if (findById.isEmpty()) {
			throw new EmployeePortalException("Empoyee not found with given id");
		}
		return findById.get();
	}
	/**
	 * this method is used to update the employee information
	 * @param empId, input for update emp
	 * @param employee, updating info
	 * @return {@link Employee}
	 */
	public Employee updateEmployee(int empId, Employee employee) {
		if (empId < 0) {
			throw new EmployeePortalException("Employee Id can't be non negative or zero");
		}
		Optional<Employee> findById = employeeRepository.findById(empId);
		if (findById.isEmpty()) {
			throw new EmployeePortalException("Empoyee not found with given id");
		} 
		Employee updateEmp = findById.get();
		updateEmp.setAge(employee.getAge());
		updateEmp.setLastname(employee.getLastname());
		updateEmp.setName(employee.getName());
		updateEmp.setSalary(employee.getSalary());
		
		return employeeRepository.save(updateEmp);
	}
	/**
	 * this method is used to delete employee by Id
	 * @param empId
	 * @return
	 */
	public String deleteEmpById(int empId) {
		if (empId < 0) {
			throw new EmployeePortalException("Employee Id can't be non negative or zero");
		}
		Optional<Employee> findById = employeeRepository.findById(empId);
		if (findById.isEmpty()) {
			throw new EmployeePortalException("Empoyee not found with given id");
		}
		employeeRepository.deleteById(empId);
		return "Deleted emp successfully ..";
	}

}
