
package org.project.gsm.src.Controllers;

import java.util.ArrayList;
import java.util.HashMap;

import org.project.gsm.src.Playware;
import org.project.gsm.src.Services.StockUpdateService;
import org.project.gsm.src.Services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/managers")
public class ManagerController {

	private final TransactionService transactionService;
	private final StockUpdateService stockUpdateService;

	@Autowired
	public ManagerController(TransactionService transactionService, StockUpdateService stockUpdateService) {
		this.transactionService = transactionService;
		this.stockUpdateService = stockUpdateService;
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

	@PutMapping("/addinv")
	public ResponseEntity<String> addInventory(@RequestBody HashMap<Playware, Integer> playwaresWithQtys) {
		ResponseEntity<String> toReturn = null;
		int succesfulAdds = stockUpdateService.addInventory(playwaresWithQtys);
		if (succesfulAdds > 0) {
			toReturn = ResponseEntity.ok("Inventory successfully updated for " + succesfulAdds
					+ " out of " + playwaresWithQtys.size() + " playwares");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("Could not add any items. Please try again.");

		}
		return toReturn;
	}

	@DeleteMapping("/removeinv")
	public ResponseEntity<String> removeInventory(@RequestBody Playware... playwaresToRemove) {
		ResponseEntity<String> toReturn = null;
		ArrayList<Playware> removedItems = stockUpdateService.removeInventory(playwaresToRemove);
		if (removedItems.size() > 0) {
			toReturn = ResponseEntity.ok("Successfully removed " + removedItems.size()
					+ " out of " + playwaresToRemove.length + " playwares");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("Could not remove any items. Please try again.");

		}
		return toReturn;
	}

}
