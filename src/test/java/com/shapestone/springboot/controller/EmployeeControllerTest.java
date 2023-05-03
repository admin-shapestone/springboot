package com.shapestone.springboot.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.shapestone.springboot.model.Employee;
import com.shapestone.springboot.service.EmployeeService;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
	
	@InjectMocks
	private EmployeeController employeeController;
	
	@Mock
	private EmployeeService employeeService;

	@Test
	public void testGetEmmployees() {
		List<Employee> empList = new ArrayList<>();
		Employee e1 = new Employee();
		e1.setAddress(null);
		e1.setAge(31);
		e1.setLastname("Test Last name");
		e1.setName("Test name");
		empList.add(e1 );
		Mockito.when(employeeService.getEmpoyeeList()).thenReturn(empList);
		
		
		List<Employee> emmployees = employeeController.getEmmployees();
		assertNotNull(emmployees, "Employee list can't be null");
		assertTrue(emmployees.size() == 1, "Expected list size is 1");
		assertTrue(emmployees.get(0).getName().equals("Test name"), "Employee name is mismatched..");
	}
	
	@Test
	public void testGetEmployeeById() {
		when(employeeService.getEmpById(anyInt())).thenReturn(new Employee());
		Employee emmployee = employeeController.getEmployeeById(101);
		assertNotNull(emmployee);
	}
	
	@Test
	public void testSaveEmployee() {
		Employee e1 = new Employee();
		e1.setAddress(null);
		e1.setAge(31);
		e1.setLastname("Test Last name");
		e1.setName("Test name");
		when(employeeService.validateAndSaveEmployee(e1)).thenReturn(e1);
		Employee postEmployee = employeeController.postEmployee(e1);
		assertNotNull(postEmployee, "Saved object is null");
	}

}
