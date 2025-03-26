package org.project.gsm.src;

public class Employee extends Person {

	public Employee(Credential inputCredentials) {
		super(inputCredentials);

	}

	public void processTransactions(Transaction... transactions) {
		  String sql = "INSERT INTO transactions (transaction_id, item_id, date_of_purchase, total) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection()) {
            for (Transaction trans : transactions) {
                for (int itemId : trans.getItemIDs()) {
                    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setInt(1, trans.getT_ID());
                        stmt.setInt(2, itemId);
                        stmt.setTimestamp(3, new Timestamp(trans.getDateOfPurchase().getTime()));
                        stmt.setDouble(4, trans.getTotal());
                        stmt.executeUpdate();
                        System.out.println("Transaction made for item ID: " + itemId);
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Cannot process the transactions: " + e.getMessage());
        }
    }
}
