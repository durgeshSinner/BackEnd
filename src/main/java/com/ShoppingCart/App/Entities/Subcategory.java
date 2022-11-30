package com.ShoppingCart.App.Entities;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Subcategory {
	@Id
	private String subcategory;

	public String getSubcategory() {
		return subcategory;
	}

	public void setSubcategory(String subcategory) {
		this.subcategory = subcategory;
	}

	public Subcategory(String subcategory) {
		super();
		this.subcategory = subcategory;
	}

	public Subcategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Subcategory [subcategory=" + subcategory + "]";
	}
	

}
