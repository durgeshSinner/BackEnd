package com.ShoppingCart.App.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Products;
import com.ShoppingCart.App.Filter.FilterService;
import com.ShoppingCart.App.Repositories.ProductRepository;

@Component
public class ProductServices {
	@Autowired
	private ProductRepository productrepository;

	public Products AddProduct(Products product) {
		Products p = productrepository.save(product);
		return p;

	}

	public Products GetProduct(int Id) {
		Products product = productrepository.findById(Id).get();
		return product;
	}

	public Products ModifyProduct(Products Product) {
		Products product = productrepository.findById(Product.getProductId()).get();
		product.setProductName(Product.getProductName());
		product.setProductDetails(Product.getProductDetails());
		product.setProductCategory(Product.getProductCategory());
		product.setProductPrice(Product.getProductPrice());
		productrepository.save(product);
		return product;

	}

	public List<Products> GetProductByCategory(String category) {
		List<Products> allproducts = productrepository.findAll();

		return allproducts.stream().filter(product -> product.getProductCategory().equals(category)).toList();

	}

	public List<Products> GetProductBySearch(String search) {
		List<Products> allproducts = productrepository.findAll();
		String[] searchstrings = search.split(" ");
		return allproducts.stream().filter(product -> {
			String name = product.getProductName();
			String[] productstrings = name.split(" ");
			int j = 0;
			for (int i = 0; i < searchstrings.length; i++) {
				for (int k = 0; k < productstrings.length; k++) {
					if (searchstrings[i].equals(productstrings[k])) {
						j++;
					}
				}
			}
			if (j != 0) {
				return true;
			} else {
				return false;
			}
		}).toList();

	}

	public List<Products> GetProductsbyFilter(String Category, FilterService filter) {
		filter.setCategory(Category);
		List<Products> productlist = this.GetProductByCategory(Category);
		List<Products> filteredproductlist = productlist.stream().filter(product -> {
			if (product.getProductPrice() >= filter.getMinPrice()
					&& product.getProductPrice() <= filter.getMaxPrice()) {
				return true;
			} else {
				return false;
			}
		}).toList();
		return filteredproductlist;
	}

	public List<Products> GetFilteredProducts(FilterService filter) {
		if (filter.getProducts().size() == 0) {
			System.out.println("ni");
			if (filter.getCategory().equals("")) {
				return productrepository.findAll().stream().filter(product -> {
					if (product.getProductPrice() >= filter.getMinPrice()
							&& product.getProductPrice() <= filter.getMaxPrice()) {
						return true;
					} else {
						return false;
					}
				}).toList();
			} else {
				if (filter.getSubCategory().equals("")) {
					return productrepository.findAll().stream().filter(product -> {
						if (product.getProductCategory().equals(filter.getCategory())) {
							return true;
						} else {
							return false;
						}
					}).filter(product -> {
						if (product.getProductPrice() >= filter.getMinPrice()
								&& product.getProductPrice() <= filter.getMaxPrice()) {
							return true;
						} else {
							return false;
						}
					}).toList();
				} else {
					return productrepository.findAll().stream().filter(product -> {
						if (product.getProductCategory().equals(filter.getCategory())) {
							return true;
						} else {
							return false;
						}
					}).filter(product -> {
						if (product.getProductSubCategory().equals(filter.getSubCategory())) {
							return true;
						} else {
							return false;
						}
					}).filter(product -> {
						if (product.getProductPrice() >= filter.getMinPrice()
								&& product.getProductPrice() <= filter.getMaxPrice()) {
							return true;
						} else {
							return false;
						}
					}).toList();
				}
			}
		} else {
			if (filter.getCategory().equals("")) {
				return filter.getProducts().stream().filter(product -> {
					if (product.getProductPrice() >= filter.getMinPrice()
							&& product.getProductPrice() <= filter.getMaxPrice()) {
						return true;
					} else {
						return false;
					}
				}).toList();
			} else {
				if (filter.getSubCategory().equals("")) {
					return filter.getProducts().stream().filter(product -> {
						if (product.getProductCategory().equals(filter.getCategory())) {
							return true;
						} else {
							return false;
						}
					}).filter(product -> {
						if (product.getProductPrice() >= filter.getMinPrice()
								&& product.getProductPrice() <= filter.getMaxPrice()) {
							return true;
						} else {
							return false;
						}
					}).toList();
				} else {
					return filter.getProducts().stream().filter(product -> {
						if (product.getProductCategory().equals(filter.getCategory())) {
							return true;
						} else {
							return false;
						}
					}).filter(product -> {
						if (product.getProductSubCategory().equals(filter.getSubCategory())) {
							return true;
						} else {
							return false;
						}
					}).filter(product -> {
						if (product.getProductPrice() >= filter.getMinPrice()
								&& product.getProductPrice() <= filter.getMaxPrice()) {
							return true;
						} else {
							return false;
						}
					}).toList();
				}
			}
		}

	}

	public List<Products> GetbySubcategories(String Category, String SubCategory) {
		List<Products> productslist = this.GetProductByCategory(Category);
		List<Products> filteredproducts = productslist.stream().filter(product -> {
			if (product.getProductSubCategory().equals(SubCategory)) {
				return true;
			} else {
				return false;
			}
		}).toList();
		return filteredproducts;
	}

	public Set<String> GetAllCategories() {
		Set<String> Categories = productrepository.findAll().stream().map(product -> product.getProductCategory())
				.collect(Collectors.toSet());
		return Categories;
	}

}
