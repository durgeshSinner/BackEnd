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
		return productrepository.findByProductCategory(category);
	}

	public List<Products> GetProductBySearch(String search) {
		return productrepository.findByProductnameLike('%'+search+'%');

	}

	
	public List<Products> GetFilteredProducts(FilterService filter) {
		if (filter.getProducts().size() == 0) {
			if(filter.getCategory().equals("")) {
				return productrepository.findByPriceFilters(filter.getMinPrice(), filter.getMaxPrice());
			}
			else {
				if(filter.getSubCategory().equals("")) {
					return productrepository.findByCategoryFilters(filter.getCategory(),filter.getMinPrice(), filter.getMaxPrice());
				}
				else {
					return productrepository.findBySubCategoryFilters(filter.getCategory(), filter.getSubCategory(), filter.getMinPrice(), filter.getMaxPrice());
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
		return productrepository.findBySubCategoryFilters(Category, SubCategory, 0, 100);
		
	}

}
