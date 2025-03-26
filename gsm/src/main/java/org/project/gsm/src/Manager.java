package org.project.gsm.src;

import java.sql.Connection;
import java.sql.SQLException;


public class Manager extends Employee {
	public Manager(Credential inputCredentials) {
		super(inputCredentials);
	}

	public int addInventory(Playware... playwares) {
		int successfulAdds = 0;
        InventoryDAO inventoryDAO = new InventoryDAO(); // Creates an instance to handle inventory database operations

 for (Playware playware : playwares) {
        try {
            String title = playware.getName();
            double price = playware.getBasePrice();
            int discount = 0;
            int quantity = 0;
            String description = "";
            String warranty = null;
            String type = playware instanceof Game ? "Game" : "Merch";

            inventoryDAO.addInventory(title, price, discount, quantity, description, warranty, type);
            successfulAdds++;
            System.out.println("Playware has been added: " + title);

        } catch (Exception e) {
            System.err.println("Failed to add the playware: " + playware.getName() + " → " + e.getMessage());
	}
 }

		return successfulAdds;

	}

	// TODO: Other methods require for there to be a warehouse.
}
