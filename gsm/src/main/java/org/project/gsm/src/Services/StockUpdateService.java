package org.project.gsm.src.Services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.Connection;
import java.sql.PreparedStatement;

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

	public ArrayList<Integer> removeInventory(Integer... playwareIDs) {
		ArrayList<Integer> successfulRemoves = new ArrayList<>();

	for (Integer id : playwareIDs) {
		try (Connection connection = DatabaseConnection.getConnection();
		     PreparedStatement stmt = connection.prepareStatement("DELETE FROM inventory WHERE inventory_id = ?")) {
			
			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				successfulRemoves.add(id);
			}
		} catch (SQLException e) {
			System.err.println("Cannot remove from inventory ID " + id + ": " + e.getMessage());
		}
	}

	return successfulRemoves;
}

	public void applyDiscount(Integer playwareID, int discountAmt) {
		try (Connection connection = DatabaseConnection.getConnection();
		     PreparedStatement stmt = connection.prepareStatement("UPDATE inventory SET discount = ? WHERE inventory_id = ?")) {
			stmt.setInt(1, discountAmt);
			stmt.setInt(2, playwareID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Cannot apply discount: " + e.getMessage());
		}
	}

	public void offerWarranty(Integer playwareID, int numMonths) {
		try (Connection connection = DatabaseConnection.getConnection();
		     PreparedStatement stmt = connection.prepareStatement(
				     "UPDATE inventory SET warranty = DATE_ADD(CURDATE(), INTERVAL ? MONTH) WHERE inventory_id = ?")) {
			stmt.setInt(1, numMonths);
			stmt.setInt(2, playwareID);
			stmt.executeUpdate();
		} catch (SQLException e) {
			System.err.println(" Erore cannot offer warranty: " + e.getMessage());
		}
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
