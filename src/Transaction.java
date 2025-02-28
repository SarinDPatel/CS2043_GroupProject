package src;

import java.util.Calendar;

public class Transaction {
	private final int T_ID; // Transaction id
	private static int T_IDCounter = 10000000;
	private String U_ID; // User id
	private int[] itemIDs;
	private Calendar dateOfPurchase; // Class recommended for date-time operations
	private Calendar warrantyExpiry;
	private double subtotal; // total before adding discounts
	private double discount; // defined from 0.0 to 1.0
	private double total;

	// TODO: Constructor and some methods
}
