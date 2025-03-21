
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
import org.springframework.web.bind.annotation.PathVariable;

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
	public ResponseEntity<String> removeInventory(@RequestBody Integer... playwaresToRemove) {
		ResponseEntity<String> toReturn = null;
		ArrayList<Integer> removedItemIds = stockUpdateService.removeInventory(playwaresToRemove);
		if (removedItemIds.size() > 0) {
			toReturn = ResponseEntity.ok("Successfully removed " + removedItemIds.size()
					+ " out of " + playwaresToRemove.length + " playwares");
		} else {
			toReturn = ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
					.body("Could not remove any items. Please try again.");

		}
		return toReturn;
	}

	@PutMapping("discount/{id}")
	public ResponseEntity<String> applyDiscount(@PathVariable Integer playwareId,
			@RequestBody Integer discountAmt) {
		ResponseEntity<String> toReturn = null;
		stockUpdateService.applyDiscount(playwareId, discountAmt);
		toReturn = ResponseEntity.ok("Applied discount to item id" + playwareId);
		return toReturn;
	}

	@PutMapping("warranty/{id}")
	public ResponseEntity<String> offerWarranty(@PathVariable Integer playwareId,
			@RequestBody Integer numMonths) {
		ResponseEntity<String> toReturn = null;
		stockUpdateService.applyDiscount(playwareId, numMonths);
		toReturn = ResponseEntity.ok("Applied warranty to item id" + playwareId);
		return toReturn;
	}
}
