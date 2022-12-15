package com.ShoppingCart.App.Filter;

public class Filter {
	private String productName;
	private String category;
	private String subCategory;
	private int minPrice;
	private int maxPrice;
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	public int getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(int minPrice) {
		this.minPrice = minPrice;
	}
	public int getMaxPrice() {
		return maxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		this.maxPrice = maxPrice;
	}
	public Filter(String productName, String category, String subCategory, int minPrice, int maxPrice) {
		super();
		this.productName = productName;
		this.category = category;
		this.subCategory = subCategory;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	public Filter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
}
