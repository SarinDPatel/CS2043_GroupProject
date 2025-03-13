package org.project.gsm.src;

//TODO: Incomplete. Still needs some instance vars and getters/setters
public class Game extends Playware {
	public Game(String name, double basePrice) {
		super();
		this.name = name;
		this.basePrice = basePrice;
		this.qtyInStock = DEFAULT_QTY_IN_STOCK;
		this.discount = DEFAULT_DISCOUNT;
		this.warrantyLengthInMonths = DEFAULT_WARRANTY;
	}

}
