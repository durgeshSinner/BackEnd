package com.ShoppingCart.App.Entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ProductId;
	private String ProductName;
	private int ProductPrice;
	private String ProductDetails;
	private String ProductCategory;
	private String ProductSubCategory;
	private String Url;
	public Products() {
		super();
	}
	public Products(int productId, String productName, int productPrice, String productDetails, String productCategory,
			String productSubCategory, String url) {
		super();
		ProductId = productId;
		ProductName = productName;
		ProductPrice = productPrice;
		ProductDetails = productDetails;
		ProductCategory = productCategory;
		ProductSubCategory = productSubCategory;
		Url = url;
	}
	@Override
	public String toString() {
		return "Products [ProductId=" + ProductId + ", ProductName=" + ProductName + ", ProductPrice=" + ProductPrice
				+ ", ProductDetails=" + ProductDetails + ", ProductCategory=" + ProductCategory
				+ ", ProductSubCategory=" + ProductSubCategory + ", Url=" + Url + "]";
	}
	public int getProductId() {
		return ProductId;
	}
	public void setProductId(int productId) {
		ProductId = productId;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public int getProductPrice() {
		return ProductPrice;
	}
	public void setProductPrice(int productPrice) {
		ProductPrice = productPrice;
	}
	public String getProductDetails() {
		return ProductDetails;
	}
	public void setProductDetails(String productDetails) {
		ProductDetails = productDetails;
	}
	public String getProductCategory() {
		return ProductCategory;
	}
	public void setProductCategory(String productCategory) {
		ProductCategory = productCategory;
	}
	public String getProductSubCategory() {
		return ProductSubCategory;
	}
	public void setProductSubCategory(String productSubCategory) {
		ProductSubCategory = productSubCategory;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	
	
	
}
