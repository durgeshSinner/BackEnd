package com.ShoppingCart.App.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Category;
import com.ShoppingCart.App.Repositories.CategoryRepository;

@Component
public class CategoryService {
	@Autowired
	private CategoryRepository repository;
	
	
	public List<Category> GetallCategories(){
		return repository.findAll();
	}

}
