package org.project.gsm.src;

import java.util.ArrayList;

public class Merch extends Playware {
	private ArrayList<String> genres = null;
	private ArrayList<String> sizes = null;

	public Merch(String name, double basePrice, ArrayList<String> genres, ArrayList<String> sizes) {
		super(name, basePrice);
		this.genres = genres;
		this.sizes = sizes;
	}

	public ArrayList<String> getGenres() {
		return genres;
	}

	public void setGenres(ArrayList<String> genres) {
		this.genres = genres;
	}

	public ArrayList<String> getSizes() {
		return sizes;
	}

	public void setSizes(ArrayList<String> sizes) {
		this.sizes = sizes;
	}

}
