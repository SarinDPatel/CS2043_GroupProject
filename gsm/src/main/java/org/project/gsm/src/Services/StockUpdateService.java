package org.project.gsm.src.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.project.gsm.src.Playware;
import org.project.gsm.src.Transaction;
import org.springframework.stereotype.Service;

@Service
public class StockUpdateService {
	/*
	 * addInv
	 * removeInv
	 * applyDisc
	 * offerWaranty
	 */

	public int addInventory(HashMap<Playware, Integer> playwaresWithQtys) {
		int successfulAdds = 0;
		for (Playware p : playwaresWithQtys.keySet()) {
			int inventoryID = checkIfExistsInDB(p);
			boolean success;
			if (inventoryID == 0) {
				success = insertIntoDB(p, playwaresWithQtys.get(p));
			} else {
				success = updateDBEntry(inventoryID, playwaresWithQtys.get(p));
			}
			successfulAdds += success ? 1 : 0;

		}
		return successfulAdds;
	}

	public ArrayList<Playware> removeInventory(Playware... playwares) {
		ArrayList<Playware> successfulRemoves = new ArrayList<>(0);
		for (Playware p : playwares) {
			int rowsAffected = 0; // TODO: Substitute for remove statement
			if (rowsAffected != 0) {
				successfulRemoves.add(p);
			}
		}
		return successfulRemoves;
	}

	public void applyDiscount(Playware playware, int discountAmt) {
		// TODO: Replace stub
		// call update statement to discount=discountAmt
	}

	public void offerWarranty(Playware playware, int numMonths) {
		// TODO: Replace stub
		// call update statement to warranty = numMonths
	}

	private int checkIfExistsInDB(Playware p) {
		ResultSet resultSet = null; // TODO: Substitute for select statement
		int inventoryID = 0;
		try {
			inventoryID = resultSet != null ? resultSet.getInt(1) : 0;

		} catch (SQLException sqle) {
			System.out.println(sqle);

		}
		return inventoryID;

	}

	private boolean insertIntoDB(Playware p, int qtyToAdd) {
		boolean success = false;

		// TODO: Replace stub

		return success;

	}

	private boolean updateDBEntry(int inventoryID, int qtyToAdd) {
		int rowCount = 0; // TODO: Replace stub with update statement

		return rowCount != 0;

	}

}
