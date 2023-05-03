package com.shapestone.springboot.service;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shapestone.springboot.exception.EmployeePortalException;
import com.shapestone.springboot.model.Employee;
import com.shapestone.springboot.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

	@Mock
	EmployeeRepository employeeRepository;
	
	@InjectMocks
	EmployeeService employeeService;
	
	@Test
	public void testUpdateEmployeeForNegativeScenario() {
		try {
			employeeService.updateEmployee(-101, new Employee());
		}
		catch (EmployeePortalException e) {
			assertTrue(e.getMessage().contains("Employee Id can't be non negative or zero"));
		}
		
	}
	
	@Test
	public void testUpdateEmployeeForEmptyScenario() {
		try {
			when(employeeRepository.findById(anyInt())).thenReturn(Optional.empty());
			employeeService.updateEmployee(101, new Employee());
		}
		catch (EmployeePortalException e) {
			assertTrue(e.getMessage().contains("Empoyee not found with given id"));
		}
		
	}
	
	@Test
	public void testUpdateEmployee() {
		when(employeeRepository.findById(anyInt())).thenReturn(Optional.of(new Employee()));
		Employee employee = new Employee();
		employee.setAge(27);
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		Employee updateEmployee = employeeService.updateEmployee(101, employee );
		
		assertTrue(updateEmployee != null);
		assertTrue(updateEmployee.getAge() == 27);
		
	}

}
