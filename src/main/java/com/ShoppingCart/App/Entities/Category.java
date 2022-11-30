package com.ShoppingCart.App.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	@Id
	private String Category;
	private String url;
	@OneToMany
	private List<Subcategory> Subcategory;
	public String getCategory() {
		return Category;
	}
	public void setCategory(String category) {
		Category = category;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Subcategory> getSubcategory() {
		return Subcategory;
	}
	public void setSubcategory(List<Subcategory> subcategory) {
		Subcategory = subcategory;
	}
	@Override
	public String toString() {
		return "Category [Category=" + Category + ", url=" + url + ", Subcategory=" + Subcategory + "]";
	}
	public Category(String category, String url, List<com.ShoppingCart.App.Entities.Subcategory> subcategory) {
		super();
		Category = category;
		this.url = url;
		Subcategory = subcategory;
	}
	public Category() {
		super();
	}
	
	
	
	
}
