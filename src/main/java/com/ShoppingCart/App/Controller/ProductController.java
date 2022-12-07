package com.ShoppingCart.App.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShoppingCart.App.Entities.Category;
import com.ShoppingCart.App.Entities.Products;
import com.ShoppingCart.App.Filter.FilterService;
import com.ShoppingCart.App.Services.CategoryService;
import com.ShoppingCart.App.Services.ProductServices;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	private ProductServices service;
	@Autowired
	private CategoryService catservice;

	@PostMapping("/addProduct")
	public ResponseEntity<Products> AddProduct(@RequestBody Products product) {
		try {
			Products p = service.AddProduct(product);
			return new ResponseEntity<Products>(p, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PostMapping("/update")
	public ResponseEntity<String> UpdateProduct(@RequestBody Products product) {
		try {
			service.ModifyProduct(product);
			return new ResponseEntity<String>("modified", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("unable to modify", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getById/{productId}")
	public ResponseEntity<Products> GetProductById(@PathVariable("productId") int Id) {
		try {
			Products product = service.GetProduct(Id);
			return new ResponseEntity<Products>(product, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	@GetMapping("/{category}")
//	public List<Products> GetProductByCategory(@PathVariable("category") String category) {
//		List <Products> p =service.GetProductByCategory(category);
//		return p;
//	}
	@GetMapping("/search/{searchString}")
	public ResponseEntity<List<Products>> GetProductBySearch(@PathVariable("searchString") String search) {
		try {
			List<Products> productslist = service.GetProductBySearch(search);
			return new ResponseEntity<List<Products>>(productslist, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

//	@PostMapping("/{category}/getFilteredProducts")
//	public List<Products> GetProductByFilter(@PathVariable("category") String Category, @RequestBody FilterService filter) {
//		System.out.println(filter);
//		System.out.println(filter.getMinPrice());
//		System.out.println(filter.getMaxPrice());
//		return service.GetProductsbyFilter(Category, filter);
//	}
	@GetMapping("/{category}/{subcategory}")
	public ResponseEntity<List<Products>> GetProductsBySubcategory(@PathVariable("category") String Category,
			@PathVariable("subcategory") String SubCategory) {
		try {
			List<Products> productsbysubcategory = service.GetbySubcategories(Category, SubCategory);
			return new ResponseEntity<List<Products>>(productsbysubcategory, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@GetMapping("/Getcategories")
	public ResponseEntity<List<Category>> GetAllCategories() {
		try {
			return new ResponseEntity<List<Category>>(catservice.GetallCategories(), HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping("/getFilteredProducts")
	public ResponseEntity<List<Products>> GetFilteredProducts(@RequestBody FilterService filter) {
		try {
			List<Products> filteredProducts = service.GetFilteredProducts(filter);
			return new ResponseEntity<List<Products>>(filteredProducts, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

}
