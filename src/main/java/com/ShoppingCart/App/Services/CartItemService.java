package com.ShoppingCart.App.Services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ShoppingCart.App.Entities.Cart;
import com.ShoppingCart.App.Entities.CartItem;
import com.ShoppingCart.App.Entities.Products;
import com.ShoppingCart.App.Repositories.CartItemRepository;
import com.ShoppingCart.App.Repositories.CartRepository;

@Component
public class CartItemService {
	@Autowired
	private Cartservice cartservice;
	@Autowired
	private CartItemRepository cartitemrepository;
	@Autowired
	private CartRepository cartrepository;
	@Autowired
	private ProductServices productservice;
	
	public CartItem getCartItem(int userId, int cartitemId) {
		return cartitemrepository.findById(cartitemId).get();
		
	}
//	public CartItem GetCartItem(int userId, int productId) {
//		return cartrepository.findCartItemByUserIdandProductId(userId, productId);
//	}
	public CartItem AddCartItem(int userId, int productId) {
		Cart cart = cartservice.GetCart(userId);
		List<CartItem> filteritems = cart.getProducts().stream().filter(item -> {
			if(item.getProduct().getProductId()==productId) {return true;}
			else {return false;}
		}).toList();
		if(filteritems.size()==0) {
			CartItem item = new CartItem();
			Products product = productservice.GetProduct(productId);
			item.setProduct(product);
			item.setQuantity(1);
			return this.AddNewCartItem(userId, item, cart);
		}
		else {
			CartItem item = filteritems.get(0);
			return this.AddExistingItem(userId, item, cart);
		}
	}
	
	public CartItem AddNewCartItem(int userId, CartItem item, Cart cart) {
		List<CartItem>items = cart.getProducts();
		items.add(item);
		cart.setProducts(items);
		CartItem i=cartitemrepository.save(item);
		cartrepository.save(cart);
		return i;
		
	}
	
	public CartItem AddExistingItem(int userId, CartItem item, Cart cart) {
		List<CartItem>items = cart.getProducts();
		for(CartItem c : items) {
			if(c.getCartItemId()==item.getCartItemId()) {
				c.setQuantity(item.getQuantity()+1);
			}
		}
		cart.setProducts(items);
		CartItem i= cartitemrepository.save(item);
		cartrepository.save(cart);
		cartitemrepository.save(item);
		return i;
	}
	public String RemoveItem(int userId, int productId) {
		Cart cart = cartservice.GetCart(userId);
		List<CartItem> items = cart.getProducts();
		Integer index= null;
		int j=0;
		for(CartItem i : items) {
			if(i.getProduct().getProductId()==productId) {
				index= items.indexOf(i);
				j++;
			}
		}
		int cartitemid = items.get(index).getCartItemId();
		String name = items.get(index).getProduct().getProductName();
		if(j==1) {items.remove((int)index);}
		cart.setProducts(items);
		cartitemrepository.deleteById(cartitemid);
		cartrepository.save(cart);
		
		
		
		return "product : " + name + " was removed";
	}
	
	@Transactional
	public void ChangeProductQuantity( int quantity, int userId , int productId) {
		
		cartrepository.updateQuantitybyUserIdandProductId(quantity, userId, productId);
	}
}
