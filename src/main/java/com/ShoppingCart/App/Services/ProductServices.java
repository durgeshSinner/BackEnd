package com.ShoppingCart.App.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Products;
import com.ShoppingCart.App.Exception.APIException;
import com.ShoppingCart.App.Filter.Filter;
import com.ShoppingCart.App.Repositories.ProductRepository;

@Component
public class ProductServices {
	@Autowired
	private EntityManager em;
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

//	public List<Products> GetFilteredProducts(Filter filter) {
//		if (filter.getProducts().size() == 0) {
//			if (filter.getCategory().equals("")) {
//				return productrepository.findByPriceFilters(filter.getMinPrice(), filter.getMaxPrice());
//			} else {
//				if (filter.getSubCategory().equals("")) {
//					return productrepository.findByCategoryFilters(filter.getCategory(), filter.getMinPrice(),
//							filter.getMaxPrice());
//				} else {
//					return productrepository.findBySubCategoryFilters(filter.getCategory(), filter.getSubCategory(),
//							filter.getMinPrice(), filter.getMaxPrice());
//				}
//			}
//		} else {
//			if (filter.getCategory().equals("")) {
//				List<Products> filteredproducts = filter.getProducts().stream().filter(product -> {
//					if (product.getProductPrice() >= filter.getMinPrice()
//							&& product.getProductPrice() <= filter.getMaxPrice()) {
//						return true;
//					} else {
//						return false;
//					}
//				}).toList();
//				if (filteredproducts.size() == 0) {
//					throw new APIException("No content");
//				} else {
//					return filteredproducts;
//				}
//
//			} else {
//				if (filter.getSubCategory().equals("")) {
//					List<Products> filteredproducts = filter.getProducts().stream().filter(product -> {
//						if (product.getProductCategory().equals(filter.getCategory())) {
//							return true;
//						} else {
//							return false;
//						}
//					}).filter(product -> {
//						if (product.getProductPrice() >= filter.getMinPrice()
//								&& product.getProductPrice() <= filter.getMaxPrice()) {
//							return true;
//						} else {
//							return false;
//						}
//					}).toList();
//					if (filteredproducts.size() == 0) {
//						throw new APIException("No content");
//					} else {
//						return filteredproducts;
//					}
//				} else {
//					List<Products> filteredproducts = filter.getProducts().stream().filter(product -> {
//						if (product.getProductCategory().equals(filter.getCategory())) {
//							return true;
//						} else {
//							return false;
//						}
//					}).filter(product -> {
//						if (product.getProductSubCategory().equals(filter.getSubCategory())) {
//							return true;
//						} else {
//							return false;
//						}
//					}).filter(product -> {
//						if (product.getProductPrice() >= filter.getMinPrice()
//								&& product.getProductPrice() <= filter.getMaxPrice()) {
//							return true;
//						} else {
//							return false;
//						}
//					}).toList();
//					if (filteredproducts.size() == 0) {
//						throw new APIException("No content");
//					} else {
//						return filteredproducts;
//					}
//				}
//			}
//		}
//
//	}
	public List<Products> GetFilteredProducts(Filter filter) {
		 CriteriaBuilder cb = em.getCriteriaBuilder();
		    CriteriaQuery<Products> cq = cb.createQuery(Products.class);

		    Root<Products> product = cq.from(Products.class);
		    List<Predicate> predicates = new ArrayList<>();
		    
		    if (filter.getProductName() != null) {
		        predicates.add(cb.like(product.get("productName"), "%" + filter.getProductName() + "%"));
		    }
		    if (filter.getCategory() != "") {
		        predicates.add(cb.equal(product.get("productCategory"), filter.getCategory()));
		    }
		    if (filter.getSubCategory() != "") {
		        predicates.add(cb.equal(product.get("productSubCategory"), filter.getSubCategory()));
		    }
		    if (filter.getMinPrice() != 0) {
		        predicates.add(cb.greaterThanOrEqualTo(product.get("productPrice"), filter.getMinPrice()));
		    }
		    if (filter.getMaxPrice() != 100) {
		        predicates.add(cb.lessThanOrEqualTo(product.get("productPrice"), filter.getMaxPrice()));
		    }
		    cq.where(predicates.toArray(new Predicate[0]));

		    return em.createQuery(cq).getResultList();
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
