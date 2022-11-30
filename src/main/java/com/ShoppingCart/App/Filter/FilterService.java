package com.ShoppingCart.App.Filter;

import java.util.List;
import com.ShoppingCart.App.Entities.Products;

public class FilterService {

	private List<Products> Products;
	private String Category;
	private String SubCategory;
	private int MinPrice;
	private int MaxPrice;
	public List<Products> getProducts() {
		return Products;
	}
	public void setProducts(List<Products> products) {
		Products = products;
	}
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getSubCategory() {
		return SubCategory;
	}
	public void setSubCategory(String subCategory) {
		SubCategory = subCategory;
	}
	public int getMinPrice() {
		return MinPrice;
	}
	public void setMinPrice(int minPrice) {
		MinPrice = minPrice;
	}
	public int getMaxPrice() {
		return MaxPrice;
	}
	public void setMaxPrice(int maxPrice) {
		MaxPrice = maxPrice;
	}
	public FilterService(List<com.ShoppingCart.App.Entities.Products> products, String category, String subCategory,
			int minPrice, int maxPrice) {
		super();
		Products = products;
		Category = category;
		SubCategory = subCategory;
		MinPrice = minPrice;
		MaxPrice = maxPrice;
	}
	public FilterService() {
		super();
	}
	@Override
	public String toString() {
		return "FilterService [Products=" + Products + ", Category=" + Category + ", SubCategory=" + SubCategory
				+ ", MinPrice=" + MinPrice + ", MaxPrice=" + MaxPrice + "]";
	}
	
	
	
	
}
