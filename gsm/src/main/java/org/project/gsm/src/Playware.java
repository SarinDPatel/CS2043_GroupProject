package org.project.gsm.src;

//FIXME: The default quantities and corresponding fields may not be the most appropriate in this class. Consider refactoring this and child classes
public abstract class Playware {
	private final int DEFAULT_QTY_IN_STOCK = 0;
	private final double DEFAULT_DISCOUNT = 0.0;
	private final int DEFAULT_WARRANTY = 6;

	private static int I_IDCounter = 10000;
	private final int I_ID;
	private String name;
	private double basePrice;
	private int qtyInStock;
	private double discount;
	private int warrantyLengthInMonths;

	public Playware() {
		this.I_ID = I_IDCounter;
		I_IDCounter++;
	}

	public int getI_ID() {
		return I_ID;
	}

	public String getName() {
		return name;
	}

	public double getBasePrice() {
		return basePrice;
	}

	public int getQtyInStock() {
		return qtyInStock;
	}

	public double getDiscount() {
		return discount;
	}

	public int getWarrantyLengthInMonths() {
		return warrantyLengthInMonths;
	}

	public int getDEFAULT_QTY_IN_STOCK() {
		return DEFAULT_QTY_IN_STOCK;
	}

	public double getDEFAULT_DISCOUNT() {
		return DEFAULT_DISCOUNT;
	}

	public int getDEFAULT_WARRANTY() {
		return DEFAULT_WARRANTY;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public void setQtyInStock(int qtyInStock) {
		this.qtyInStock = qtyInStock;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public void setWarrantyLengthInMonths(int warrantyLengthInMonths) {
		this.warrantyLengthInMonths = warrantyLengthInMonths;
	}
}
