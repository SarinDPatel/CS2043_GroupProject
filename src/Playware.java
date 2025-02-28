package src;

// TODO: Write some exceptions to validate inputs.
public abstract class Playware {
	protected final int DEFAULT_QTY_IN_STOCK = 0;
	protected final double DEFAULT_DISCOUNT = 0.0;
	protected final int DEFAULT_WARRANTY = 6;

	protected static int I_IDCounter = 1000;
	protected final int I_ID;
	protected String name;
	protected double basePrice;
	protected int qtyInStock;
	protected double discount;
	protected int warrantyLengthInMonths;

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
