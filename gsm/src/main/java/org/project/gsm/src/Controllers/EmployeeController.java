package org.project.gsm.src.Controllers;

import java.util.ArrayList;

import org.project.gsm.src.Employee;
import org.project.gsm.src.Playware;
import org.project.gsm.src.Services.EmployeeService;
import org.project.gsm.src.Services.TransactionService;
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

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	private final TransactionService transactionService;
	private final EmployeeService employeeService;

	@Autowired
	public EmployeeController(TransactionService transactionService, EmployeeService employeeService) {
		this.transactionService = transactionService;
		this.employeeService = employeeService;
	}

	@PostMapping("/checkout")
	public ResponseEntity<String> checkout(@RequestBody ArrayList<Playware> items) {
		boolean success = transactionService.processTransaction(items);
		ResponseEntity<String> toReturn = null;
		if (success) {
			toReturn = ResponseEntity.ok("Checkout successful");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Processing transactions caused error");
		}
		return toReturn;
	}

	@GetMapping
	public ResponseEntity<ArrayList<Employee>> getAllEmployees() {
		ArrayList<Employee> emps = employeeService.getAllEmployees();
		ResponseEntity<ArrayList<Employee>> toReturn = null;
		if (emps != null) {
			toReturn = ResponseEntity.ok(emps);
		} else {
			toReturn = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		return toReturn;

	}

	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		Employee emp = employeeService.getEmployeeById(id);
		ResponseEntity<Employee> toReturn = null;
		if (emp != null) {
			toReturn = ResponseEntity.ok(emp);
		} else {
			toReturn = ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(null);
		}
		return toReturn;
	}

	@PostMapping
	public ResponseEntity<String> createEmployee(@RequestBody Employee newEmployee) {
		boolean success = employeeService.createEmployee(newEmployee);
		ResponseEntity<String> toReturn = null;
		if (success) {
			toReturn = ResponseEntity.ok("Successfully added the employee");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("Adding employee failed");
		}
		return toReturn;

	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
		boolean success = employeeService.updateEmployeeInDB(id, employee);
		if (success) {
			return ResponseEntity.ok("Employee updated successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Employee not found");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable String id) {
		boolean success = employeeService.deleteEmployeeFromDB(id);
		if (success) {
			return ResponseEntity.ok("Employee deleted successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Employee not found");
	}

}
