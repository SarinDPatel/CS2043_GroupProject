package org.project.gsm.src.Controllers;

import java.util.ArrayList;

import org.project.gsm.src.Playware;
import org.project.gsm.src.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {

	private final TransactionService transactionService;

	@Autowired
	public EmployeeController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@PostMapping("/checkout")
	public ResponseEntity<String> checkout(@RequestBody ArrayList<Playware> items) {
		boolean success = transactionService.processTransaction(items);
		ResponseEntity<String> toReturn = null;
		if (success) {
			toReturn = ResponseEntity.ok("Login successful");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Processing transactions caused error");
		}
		return toReturn;
	}

}
