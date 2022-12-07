package com.ShoppingCart.App.Services;

import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Products;
import com.ShoppingCart.App.Exception.APIException;
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
		return productrepository.findById(Id).get();
	}

	@Transactional
	public void ModifyProduct(Products Product) {
		productrepository.updateProducts(Product.getProductCategory(), Product.getProductSubCategory(),
				Product.getProductDetails(), Product.getProductName(), Product.getProductId(), Product.getUrl(),
				Product.getProductPrice());

	}

	public List<Products> GetProductByCategory(String category) {
		return productrepository.findByProductCategory(category);
	}

	public List<Products> GetProductBySearch(String search) throws NoSuchElementException {
		List<Products> searchproducts = productrepository.findByProductnameLike('%' + search + '%');
		if (searchproducts.size() == 0) {
			throw new NoSuchElementException();
		} else {
			return searchproducts;
		}

	}

	public List<Products> GetFilteredProducts(FilterService filter) {
		if (filter.getProducts().size() == 0) {
			if (filter.getCategory().equals("")) {
				return productrepository.findByPriceFilters(filter.getMinPrice(), filter.getMaxPrice());
			} else {
				if (filter.getSubCategory().equals("")) {
					return productrepository.findByCategoryFilters(filter.getCategory(), filter.getMinPrice(),
							filter.getMaxPrice());
				} else {
					return productrepository.findBySubCategoryFilters(filter.getCategory(), filter.getSubCategory(),
							filter.getMinPrice(), filter.getMaxPrice());
				}
			}
		} else {
			if (filter.getCategory().equals("")) {
				List<Products> filteredproducts = filter.getProducts().stream().filter(product -> {
					if (product.getProductPrice() >= filter.getMinPrice()
							&& product.getProductPrice() <= filter.getMaxPrice()) {
						return true;
					} else {
						return false;
					}
				}).toList();
				if (filteredproducts.size() == 0) {
					throw new APIException("No content");
				} else {
					return filteredproducts;
				}

			} else {
				if (filter.getSubCategory().equals("")) {
					List<Products> filteredproducts = filter.getProducts().stream().filter(product -> {
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
					if (filteredproducts.size() == 0) {
						throw new APIException("No content");
					} else {
						return filteredproducts;
					}
				} else {
					List<Products> filteredproducts = filter.getProducts().stream().filter(product -> {
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
					if (filteredproducts.size() == 0) {
						throw new APIException("No content");
					} else {
						return filteredproducts;
					}
				}
			}
		}

	}

	public List<Products> GetbySubcategories(String Category, String SubCategory) {
		List<Products> products = productrepository.findBySubCategory(Category, SubCategory);
		if (products.size() == 0) {
			throw new NoSuchElementException();
		} else {
			return products;
		}

	}

}
