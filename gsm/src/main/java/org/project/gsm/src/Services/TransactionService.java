package org.project.gsm.src.Services;

import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.project.gsm.src.Employee;
import org.project.gsm.src.Playware;
import org.project.gsm.src.Transaction;

@Service
public class TransactionService {

	public boolean processTransaction(ArrayList<Playware> itemsToProcess) {
		boolean success = true;
		double total = getTotalPrice(itemsToProcess);

		ArrayList<Integer> itemIds = new ArrayList<>(0);
		for (Playware i : itemsToProcess) {
			itemIds.add(i.getI_ID());
		}
		Transaction t = new Transaction(itemIds, total);
		try {
			insertTransaction(t);
		} catch (Exception e) {
			success = false;
			System.out.println(e);
		}
		return success;

	}

	private double getTotalPrice(ArrayList<Playware> items) {
		double total = 0.0;
		for (Playware i : items) {
			ResultSet details = getItemDetailsFromDB(i);
			try {
				double thisPrice = details.getInt("price");
				double thisDiscount = details.getInt("discount");
				total += thisPrice - (thisPrice * thisDiscount);
			} catch (SQLException sqle) {
				System.out.println(sqle);
			}
		}
		return total;

	}

	private ResultSet getItemDetailsFromDB(Playware item) {
		// TODO: Replace stub
		// Finish implementation
		return null;
	}

	private void insertTransaction(Transaction toInsert) {
		// TODO: Replace stub
	}
}
