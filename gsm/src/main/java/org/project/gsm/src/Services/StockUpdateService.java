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
		try (Connection connection = DatabaseConnection.getConnection();
		    PreparedStatement stmt = connection.prepareStatement(
			    "SELECT inventory_id FROM inventory WHERE title = ?")) {
			stmt.setString(1, p.getName());
		ResultSet resultSet = stmt.executeQuery(); 
		if (resultSet.next()) {
				return resultSet.getInt("inventory_id");
			}
		} catch (SQLException e) {
			System.err.println("Cannot find playware in DB: " + e.getMessage());
		}
		return 0;
	}

	private boolean insertIntoDB(Playware p, int qtyToAdd) {
		String sql = "INSERT INTO inventory (title, price, discount, quantity_in_stock, description, warranty, type) VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (Connection connection = DatabaseConnection.getConnection();
		     PreparedStatement stmt = connection.prepareStatement(sql)) {

			stmt.setString(1, p.getName());
			stmt.setDouble(2, p.getBasePrice());
			stmt.setInt(3, 0); 
			stmt.setInt(4, qtyToAdd);
			stmt.setString(5, ""); 
			stmt.setDate(6, null); 
			stmt.setString(7, p instanceof Game ? "Game" : "Merch");
			int rows = stmt.executeUpdate();
			return rows > 0;

		} catch (SQLException e) {
			System.err.println("Error cannot insert playware: " + e.getMessage());
			return false;
		}
	}

	private boolean updateDBEntry(int inventoryID, int qtyToAdd) {
		String sql = "UPDATE inventory SET quantity_in_stock = quantity_in_stock + ? WHERE inventory_id = ?";
		try (Connection connection = DatabaseConnection.getConnection();
		     PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, qtyToAdd);
			stmt.setInt(2, inventoryID);
			int rows = stmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			System.err.println("Cannot update inventory: " + e.getMessage());
			return false;
		}
	}

}
