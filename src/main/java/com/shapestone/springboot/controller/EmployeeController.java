package com.shapestone.springboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shapestone.springboot.model.Employee;
import com.shapestone.springboot.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/list")
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public List<Employee> getEmmployees() {
		log.info("In Employee controller, requested {/list} web method");
		return employeeService.getEmpoyeeList();
	}

	@GetMapping("/{empId}")
	public Employee getEmployeeById(@PathVariable("empId") int empId) {
		return employeeService.getEmpById(empId);

	}

	@GetMapping("/name/{empId}")
	public Employee getEmployeeByName(@PathVariable("empId") String empName) {
		return employeeService.getEmpByName(empName);

	}

	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public Employee postEmployee(@RequestBody Employee employee) {
		log.info("In Employee controller, requested {/} post :: web method");
		return employeeService.validateAndSaveEmployee(employee);
	}

	@PutMapping("/{empId}")
	public Employee updateEmployee(@PathVariable("empId") int empId, @RequestBody Employee employee) {
		log.info("In Employee controller, requested {/} put :: web method");
		return employeeService.updateEmployee(empId, employee);
	}

	@DeleteMapping("/{empId}")
	public String deleteEmployee(@PathVariable("empId") int empId) {
		log.info("In Employee controller, requested {/} put :: web method");
		return employeeService.deleteEmpById(empId);
	}
}
