package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Content;
import domain.Item;
import domain.Order;
import domain.ShoppingCart;

import repositories.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartService {

 	//Managed repository -----------------------------------------------------

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	//Supporting services ----------------------------------------------------

	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ContentService contentService;
	
	@Autowired
	private OrderService orderService;
	
	//Constructors -----------------------------------------------------------

	public ShoppingCartService(){
		super();
	}
	
	//Simple CRUD methods ----------------------------------------------------

	public boolean exists(ShoppingCart shoppingCart){
		boolean result;
		
		result = shoppingCartRepository.exists(shoppingCart.getId());
		
		return result;
	}
	//Other business methods -------------------------------------------------
 
	public Order checkOut(ShoppingCart shoppingCart){
		Order result;
		
		result = null;
		
		System.out.println("El método checkOut dentro de ShoppingCartService no está finalizado");
		
		return result;
	}
	
	public void AddItemQuantity(ShoppingCart shoppingCart, Item item){
		contentService.createByShoppingCartAndItem(shoppingCart, item);
	}
	
	public void ChangeItemQuantity(ShoppingCart shoppingCart, Item item, int quantity){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, quantity);
	}
	
	public void DeleteItemQuantity(ShoppingCart shoppingCart, Item item){
		contentService.updateQuantityByShoppingCartAndItem(shoppingCart, item, 0);
	}
	
	public void AddComment(ShoppingCart shoppingCart, String comment){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		shoppingCart.addComment(comment);
		shoppingCartRepository.save(shoppingCart);
	}
	
	public void deleteComment(ShoppingCart shoppingCart, String comment){
		Assert.notNull(shoppingCart);
		System.out.println("El método deleteComment en ShoppingCartService no está implementado");
	}
	
	public Order checkOut(ShoppingCart shoppingCart){
		Assert.notNull(shoppingCart);
		Assert.isTrue(shoppingCart.getId() != 0);
		
		Order result;
		
		result = orderService.createFromShoppingCart(shoppingCart);
		
		orderService.save(result);
		
		return result;
		
	}
}
