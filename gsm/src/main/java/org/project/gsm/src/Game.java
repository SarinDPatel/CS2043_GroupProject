package org.project.gsm.src;

import java.util.ArrayList;

public class Game extends Playware {
	private ArrayList<String> consoles = null;
	private ArrayList<String> categories = null;

	public Game(String name, double basePrice, ArrayList<String> consoles, ArrayList<String> categories) {
		super(name, basePrice);
		this.consoles = consoles;
		this.categories = categories;
	}

	public ArrayList<String> getConsoles() {
		return consoles;
	}

	public void setConsoles(ArrayList<String> consoles) {
		this.consoles = consoles;
	}

	public ArrayList<String> getCategories() {
		return categories;
	}

	public void setCategories(ArrayList<String> categories) {
		this.categories = categories;
	}

}
