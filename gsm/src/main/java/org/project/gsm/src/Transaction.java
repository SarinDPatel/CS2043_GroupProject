package org.project.gsm.src;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Transaction {
	private final int T_ID; // Transaction id
	private static int T_IDCounter = 10000000;

	private ArrayList<Integer> itemIDs;
	private Date dateOfPurchase; // Class recommended for date-time operations
	private double total;

	public Transaction(ArrayList<Integer> itemIDs, double total) {
		T_ID = T_IDCounter;
		T_IDCounter++;

		this.itemIDs = itemIDs;
		this.total = total;
		Calendar c = Calendar.getInstance();
		dateOfPurchase = c.getTime();
	}
}
