package com.ShoppingCart.App.Filter;

import java.util.List;
import com.ShoppingCart.App.Entities.Products;

public class FilterService {

	private List<Products> products;
	private String category;
	private String subCategory;
	private int minPrice;
	private int maxPrice;
	public List<Products> getProducts() {
		return products;
	}
	public void setProducts(List<Products> products) {
		this.products = products;
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
	public FilterService(List<Products> products, String category, String subCategory, int minPrice, int maxPrice) {
		super();
		this.products = products;
		this.category = category;
		this.subCategory = subCategory;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}
	public FilterService() {
		super();
	}
	@Override
	public String toString() {
		return "FilterService [products=" + products + ", category=" + category + ", subCategory=" + subCategory
				+ ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + "]";
	}
	
	
	
	
	
}
