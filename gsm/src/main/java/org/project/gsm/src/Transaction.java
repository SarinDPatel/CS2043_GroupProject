package org.project.gsm.src;

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

	public Transaction(String u_ID, int[] itemIDs, double subtotal, double discount) {
		T_ID = T_IDCounter;
		T_IDCounter++;
		U_ID = u_ID;

		this.itemIDs = itemIDs;
		this.subtotal = subtotal;
		this.discount = discount;

	}

	// TODO: Constructor and some methods
}
