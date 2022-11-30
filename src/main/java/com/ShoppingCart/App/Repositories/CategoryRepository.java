package com.ShoppingCart.App.Repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Category;


@Component
public interface CategoryRepository extends JpaRepository<Category , String> {

}
