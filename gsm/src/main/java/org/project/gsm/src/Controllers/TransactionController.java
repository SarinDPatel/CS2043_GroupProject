package org.project.gsm.src.Controllers;

import java.util.ArrayList;
import org.project.gsm.src.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {

	@GetMapping
	public ResponseEntity<ArrayList<Transaction>> getAllTransactions() {
		ArrayList<Transaction> transactions = getTransactionsFromDB();
		return ResponseEntity.ok(transactions);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Transaction> getTransactionById(@PathVariable String id) {
		Transaction transaction = findTransactionById(id);
		if (transaction != null) {
			return ResponseEntity.ok(transaction);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}

	@PostMapping
	public ResponseEntity<String> createTransaction(@RequestBody Transaction transaction) {
		boolean success = saveTransaction(transaction);
		if (success) {
			return ResponseEntity.ok("Transaction created successfully");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body("Failed to create transaction");
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
		boolean success = updateTransactionInDB(id, transaction);
		if (success) {
			return ResponseEntity.ok("Transaction updated successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Transaction not found");
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTransaction(@PathVariable String id) {
		boolean success = deleteTransactionFromDB(id);
		if (success) {
			return ResponseEntity.ok("Transaction deleted successfully");
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body("Transaction not found");
	}

	/* Utilities and Stubs */
	private ArrayList<Transaction> getTransactionsFromDB() {
		// TODO: Implement database fetch
		return new ArrayList<>();
	}

	private Transaction findTransactionById(String id) {
		// TODO: Implement database fetch
		return null;
	}

	private boolean saveTransaction(Transaction transaction) {
		// TODO: Implement database save
		return true;
	}

	private boolean updateTransactionInDB(String id, Transaction transaction) {
		// TODO: Implement database update
		return true;
	}

	private boolean deleteTransactionFromDB(String id) {
		// TODO: Implement database delete
		return true;
	}
}
