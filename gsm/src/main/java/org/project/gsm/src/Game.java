package org.project.gsm.src;

//TODO: Incomplete. Still needs some instance vars and getters/setters
public class Game extends Playware {

	public Game(String name, double basePrice) {
		super();
		setName(name);
		setBasePrice(basePrice);
		setQtyInStock(getDEFAULT_QTY_IN_STOCK());
		setDiscount(getDEFAULT_DISCOUNT());
		setWarrantyLengthInMonths(getDEFAULT_WARRANTY());
	}

}
