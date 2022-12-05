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
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		
		
	}
	
	@PostMapping("/update")
	public void UpdateProduct(@RequestBody Products product) {
		service.ModifyProduct(product);
	}
	@GetMapping("/getById/{productId}")
	public Products GetProductById(@PathVariable("productId") int Id) {
		return service.GetProduct(Id);
	}
//	@GetMapping("/{category}")
//	public List<Products> GetProductByCategory(@PathVariable("category") String category) {
//		List <Products> p =service.GetProductByCategory(category);
//		return p;
//	}
	@GetMapping("/search/{searchString}")
	public List<Products> GetProductBySearch(@PathVariable("searchString") String search) {
		return service.GetProductBySearch(search);
	}
//	@PostMapping("/{category}/getFilteredProducts")
//	public List<Products> GetProductByFilter(@PathVariable("category") String Category, @RequestBody FilterService filter) {
//		System.out.println(filter);
//		System.out.println(filter.getMinPrice());
//		System.out.println(filter.getMaxPrice());
//		return service.GetProductsbyFilter(Category, filter);
//	}
	@GetMapping("/{category}/{subcategory}")
	public List<Products> GetProductsBySubcategory(@PathVariable("category") String Category,@PathVariable("subcategory") String SubCategory)
	{
		return service.GetbySubcategories(Category, SubCategory);
	}
	@GetMapping("/Getcategories")
	public List<Category> GetAllCategories(){
		return catservice.GetallCategories();
	}
	@PostMapping("/getFilteredProducts")
	public List<Products> GetFilteredProducts(@RequestBody FilterService filter){
		System.out.println(filter.getProducts().stream().map(product -> {System.out.println(product.getProductName()); return product;}));
		return service.GetFilteredProducts(filter);
	}

}
